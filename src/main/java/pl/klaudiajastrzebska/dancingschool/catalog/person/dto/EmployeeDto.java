package pl.klaudiajastrzebska.dancingschool.catalog.person.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class EmployeeDto {
    String login;
    String firstName;
    String lastName;
    String gender;
    String description;
    String schoolNameAndAddress;
}
