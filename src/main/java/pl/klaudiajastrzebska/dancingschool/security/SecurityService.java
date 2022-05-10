package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserDto;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.exception.ObtainUserDataException;
import pl.klaudiajastrzebska.dancingschool.security.mapper.UserMapper;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;

    public UserDto getUser(String login) {
        Optional<UserEntity> userEntityOptional = userRepository.findByLoginAndRemovalDateNull(login);

        if (userEntityOptional.isEmpty()) {
            throw new ObtainUserDataException("Użytkownik " + login + " nie istnieje.");
        }

        UserEntity userEntity = userEntityOptional.get();

        return UserMapper.mapToDto(userEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAllActiveUsers("USER")
                .stream()
                .map(UserMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(String userLogin) {
        UserEntity user = userRepository.findByLoginAndRemovalDateNull(userLogin).get();
        user.setRemovalDate(LocalDateTime.now());
    }
}
