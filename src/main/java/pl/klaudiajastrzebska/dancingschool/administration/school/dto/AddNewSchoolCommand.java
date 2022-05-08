package pl.klaudiajastrzebska.dancingschool.administration.school.dto;

import lombok.Builder;
import lombok.Value;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class AddNewSchoolCommand {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String name;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String description;
}
