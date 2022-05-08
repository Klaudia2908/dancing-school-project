package pl.klaudiajastrzebska.dancingschool.security;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@Builder
class RegisterCommand {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String username;

    @Size(min = 8, message = ValidationMessages.PASSWORD_TOO_SHORT)
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String password;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String firstName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String lastName;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String gender;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;
}
