package pl.klaudiajastrzebska.dancingschool.security.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class UserDto {
    String login;
    String password;
    UserRoleDto role;
    LocalDateTime removalDate;
}
