package pl.klaudiajastrzebska.dancingschool.security.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String login;
    String password;
    UserRoleDto role;
}
