package pl.klaudiajastrzebska.dancingschool.catalog.person.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateInstructorCommand {
    String firstName;
    String lastName;
    LocalDate birthDate;
    String gender;
    String description;
    String schoolIdentifier;
}
