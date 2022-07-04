package pl.klaudiajastrzebska.dancingschool.catalog.instructors.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class AddInstructorCommand {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String firstName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String lastName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String description;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String gender;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
}
