package pl.klaudiajastrzebska.dancingschool.catalog.person;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import pl.klaudiajastrzebska.dancingschool.administration.employees.dto.CreateEmployeeRequest;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.*;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonTypeEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.SchoolEmployeeEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolAddressRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.mapper.SchoolMapper;
import pl.klaudiajastrzebska.dancingschool.security.UserRepository;
import pl.klaudiajastrzebska.dancingschool.security.UserRolesRepository;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployeeUserService {
    private static final String DEFAULT_EMPLOYEE_NEW_PASSWORD = "$2a$12$GoE4ceEe8C7.Sij.0/BRbuCtqOXQy2OEKeuRwbsr32FUWLe3T6bDW";
    private static final String EMPLOYEE_ROLE = "EMPLOYEE";

    private final ValidationService validationService;
    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;
    private final SchoolEmployeeRepository schoolEmployeeRepository;
    private final SchoolAddressRepository schoolAddressRepository;
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public PersonEntity addNewPerson(AddNewPersonCommand command) {
        PersonEntity personEntity = preparePersonEntity(command);

        return personRepository.save(personEntity);
    }

    public List<EmployeeDto> getInstructorsForGivenSchoolIdentifier(String schoolIdentifier) {
        return schoolEmployeeRepository.findAllInstructorsForSchoolIdentifier(schoolIdentifier)
                .stream()
                .map(SchoolEmployeeEntity::toDto)
                .toList();
    }

    public boolean employeeExistsWithinSchoolAddress(String schoolIdentifier, String employeeUserName) {
        return schoolEmployeeRepository.findBySchoolIdentifierAndUserName(schoolIdentifier, employeeUserName).isPresent();
    }

    public List<SchoolDto> getSchoolsForEmployee(String employeeUserName) {
        return schoolEmployeeRepository.findAllSchoolsForGivenEmployee(employeeUserName)
                .stream()
                .map(SchoolMapper::mapToDto)
                .toList();
    }

    private PersonEntity preparePersonEntity(AddNewPersonCommand command) {
        Optional<PersonTypeEntity> value = personTypeRepository.findByValue(command.getPersonType().name());

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(command.getFirstName());
        personEntity.setLastName(command.getLastName());
        personEntity.setGender(Strings.toRootUpperCase(command.getGender().substring(0,1)));
        personEntity.setBirthDate(command.getBirthDate());
        personEntity.setDescription(command.getDescription());
        if (command.getUserId() != null) {
            personEntity.setUser(userRepository.findById(command.getUserId()).get());
        }
        personEntity.setPersonType(value.get());

        return personEntity;
    }

    public List<EmployeeDto> getAllEmployees() {
        return schoolEmployeeRepository
                .findAllEmployees()
                .stream()
                .map(SchoolEmployeeEntity::toDto)
                .toList();
    }

    public List<PersonDto> getAllEmployeesWithoutAttachedSchool() {
        return personRepository.findAllEmployeesWithoutSchool()
                .stream()
                .map(PersonEntity::toDto)
                .collect(Collectors.toList());
    }

    public void createEmployee(CreateEmployeeRequest request) {
        validationService.validate(request);
        UserEntity employeeUser = createAndSaveNewEmployeeUser(request);

        PersonTypeEntity personTypeEmployee = personTypeRepository.findByValue(PersonType.EMPLOYEE.name()).get();
        PersonEntity employee = new PersonEntity();

        employee.setUser(employeeUser);
        employee.setBirthDate(request.getBirthDate());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setPersonType(personTypeEmployee);
        employee.setGender(Strings.toRootUpperCase(request.getGender().substring(0,1)));

        personRepository.save(employee);
    }

    public void createInstructor(CreateInstructorCommand command) {
        AddNewPersonCommand addNewPersonCommand = AddNewPersonCommand
                .builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .description(command.getDescription())
                .birthDate(command.getBirthDate())
                .gender(command.getGender())
                .personType(PersonType.INSTRUCTOR)
                .build();

        PersonEntity savedInstructor = addNewPerson(addNewPersonCommand);
        SchoolAddressEntity schoolAddress = schoolAddressRepository.findSchoolByIdentifier(command.getSchoolIdentifier()).get();

        SchoolEmployeeEntity schoolEmployeeEntity = new SchoolEmployeeEntity();
        schoolEmployeeEntity.setSchool(schoolAddress);
        schoolEmployeeEntity.setEmployee(savedInstructor);

        schoolEmployeeRepository.save(schoolEmployeeEntity);
    }

    private UserEntity createAndSaveNewEmployeeUser(CreateEmployeeRequest request) {
        UserEntity userEntity = new UserEntity();
        UserRolesEntity userRole = userRolesRepository.findByName(EMPLOYEE_ROLE).get();

        userEntity.setLogin(request.getLogin());
        userEntity.setPassword(DEFAULT_EMPLOYEE_NEW_PASSWORD);
        userEntity.setRole(userRole);

        return userRepository.save(userEntity);
    }

    public void attachEmployeeToSchool(String employeeLogin, String schoolIdentifier) {
        SchoolAddressEntity schoolAddress = schoolAddressRepository.findSchoolByIdentifier(schoolIdentifier).get();
        PersonEntity employee = personRepository.findByAttachedUserName(employeeLogin).get();

        SchoolEmployeeEntity schoolEmployeeEntity = new SchoolEmployeeEntity();
        schoolEmployeeEntity.setEmployee(employee);
        schoolEmployeeEntity.setSchool(schoolAddress);
        schoolEmployeeEntity.setEmploymentStartDate(LocalDate.now());

        schoolEmployeeRepository.save(schoolEmployeeEntity);
    }

    @Transactional
    public void deleteEmployee(String employeeLogin, String schoolIdentifier) {
        SchoolEmployeeEntity schoolEmployee = schoolEmployeeRepository.findEmployeeWithLoginAndSchoolIdentifier(employeeLogin, schoolIdentifier).get();
        schoolEmployee.setEmploymentEndDate(LocalDate.now());
    }
}
