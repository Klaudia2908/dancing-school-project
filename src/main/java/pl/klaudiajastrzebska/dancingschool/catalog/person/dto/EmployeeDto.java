package pl.klaudiajastrzebska.dancingschool.catalog.person.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class EmployeeDto {
    long personId;
    String login;
    String firstName;
    String lastName;
    String gender;
    String description;
    LocalDate birthDate;
    String schoolNameAndAddress;
    String schoolIdentifier;
}
