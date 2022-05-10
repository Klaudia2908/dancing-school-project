package pl.klaudiajastrzebska.dancingschool.security.mapper;

import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserDto;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserRoleDto;

public class UserMapper {

    public static UserDto mapToDto(UserEntity entity) {
        return UserDto
                .builder()
                .login(entity.getLogin())
                .password(entity.getPassword())
                .role(mapToDto(entity.getRole()))
                .removalDate(entity.getRemovalDate())
                .build();
    }

    static UserRoleDto mapToDto(UserRolesEntity entity) {
        return UserRoleDto
                .builder()
                .name(entity.getName())
                .build();
    }
}
