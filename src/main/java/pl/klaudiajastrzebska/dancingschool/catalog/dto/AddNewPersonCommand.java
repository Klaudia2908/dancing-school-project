package pl.klaudiajastrzebska.dancingschool.catalog.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class AddNewPersonCommand {
    String firstName;
    String lastName;
    LocalDate birthDate;
    String gender;
    String description;
    PersonType personType;
    Long userId;
}
