package pl.klaudiajastrzebska.dancingschool.security;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Value
@Builder
public class RegisterCommand {
    String username;
    String password;
    String firstName;
    String lastName;
    String gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate birthDate;
}
