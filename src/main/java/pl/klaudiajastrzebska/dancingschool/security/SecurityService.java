package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserDto;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.exception.ObtainUserDataException;
import pl.klaudiajastrzebska.dancingschool.security.mapper.UserMapper;

import java.util.Optional;

@RequiredArgsConstructor
class SecurityService {
    private final UserRepository userRepository;

    UserDto getUser(String login) {
        Optional<UserEntity> userEntityOptional = userRepository.findByLogin(login);

        if (userEntityOptional.isEmpty()) {
            throw new ObtainUserDataException("Użytkownik " + login + " nie istnieje.");
        }

        UserEntity userEntity = userEntityOptional.get();

        return UserMapper.mapToDto(userEntity);
    }
}
