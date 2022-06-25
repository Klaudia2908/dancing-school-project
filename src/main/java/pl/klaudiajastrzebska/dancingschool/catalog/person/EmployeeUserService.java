package pl.klaudiajastrzebska.dancingschool.catalog.person;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.administration.employees.dto.CreateEmployeeRequest;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.EmployeeDto;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.PersonDto;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.PersonType;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonTypeEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.SchoolEmployeeEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.mapper.SchoolMapper;
import pl.klaudiajastrzebska.dancingschool.security.UserRepository;
import pl.klaudiajastrzebska.dancingschool.security.UserRolesRepository;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployeeUserService {
    private static final String DEFAULT_EMPLOYEE_NEW_PASSWORD = "$2a$10$lXojkbR.q2zWWqlTgjQMUu1EZu7TD.vBZ03VC.ycrcxft2sr7qVmG";
    private static final String EMPLOYEE_ROLE = "EMPLOYEE";

    private final ValidationService validationService;
    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;
    private final SchoolEmployeeRepository schoolEmployeeRepository;
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;

    public void addNewPerson(AddNewPersonCommand command) {
        PersonEntity personEntity = preparePersonEntity(command);

        personRepository.save(personEntity);
    }

    public boolean employeeExistsWithinSchoolAddress(String schoolIdentifier, String employeeUserName){
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
        personEntity.setGender(command.getGender());
        personEntity.setBirthDate(command.getBirthDate());
        personEntity.setDescription(command.getDescription());
        personEntity.setUser(userRepository.findById(command.getUserId()).get());
        personEntity.setPersonType(value.get());

        return personEntity;
    }

    public List<EmployeeDto> getAllEmployees() {
        return schoolEmployeeRepository
                .findAll()
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
        employee.setDescription("");
        employee.setPersonType(personTypeEmployee);
        employee.setGender(request.getGender());

        personRepository.save(employee);
    }

    private UserEntity createAndSaveNewEmployeeUser(CreateEmployeeRequest request) {
        UserEntity userEntity = new UserEntity();
        UserRolesEntity userRole = userRolesRepository.findByName(EMPLOYEE_ROLE).get();

        userEntity.setLogin(request.getLogin());
        userEntity.setPassword(DEFAULT_EMPLOYEE_NEW_PASSWORD);
        userEntity.setRole(userRole);

        return userRepository.save(userEntity);
    }
}
