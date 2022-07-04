package pl.klaudiajastrzebska.dancingschool.catalog.instructors;


import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.instructors.dto.AddInstructorCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.instructors.dto.InstructorDto;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.CreateInstructorCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.EmployeeDto;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.util.List;

@RequiredArgsConstructor
public class InstructorService {
    private final EmployeeUserService employeeUserService;
    private final ValidationService validationService;

    public List<InstructorDto> getInstructorsForSchool(String schoolIdentifier) {
        return employeeUserService.getInstructorsForGivenSchoolIdentifier(schoolIdentifier)
                .stream()
                .map(this::mapFromEmployeeDto)
                .toList();
    }

    private InstructorDto mapFromEmployeeDto(EmployeeDto employeeDto) {
        return InstructorDto
                .builder()
                .id(employeeDto.getPersonId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .gender(employeeDto.getGender())
                .description(employeeDto.getDescription())
                .birthDate(employeeDto.getBirthDate())
                .build();
    }

    public void addInstructor(AddInstructorCommand command, String schoolIdentifier) {
        validationService.validate(command);
        employeeUserService.createInstructor(buildCreateInstructorCommand(command, schoolIdentifier));
    }

    private CreateInstructorCommand buildCreateInstructorCommand(AddInstructorCommand command, String schoolIdentifier) {
        return CreateInstructorCommand
                .builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .description(command.getDescription())
                .birthDate(command.getBirthDate())
                .gender(command.getGender())
                .schoolIdentifier(schoolIdentifier)
                .build();
    }
}
