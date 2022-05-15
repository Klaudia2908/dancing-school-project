package pl.klaudiajastrzebska.dancingschool.administration.employees.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class CreateEmployeeRequest {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String firstName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String lastName;

    @NotNull(message = ValidationMessages.NOT_BLANK)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String login;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String gender;
}
