package pl.klaudiajastrzebska.dancingschool.catalog.instructors.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class InstructorDto {
    long id;
    String firstName;
    String lastName;
    String gender;
    String description;
    LocalDate birthDate;
}
