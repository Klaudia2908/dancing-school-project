package pl.klaudiajastrzebska.dancingschool.security.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserRoleDto {
    String name;
}
