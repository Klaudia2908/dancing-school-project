package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.catalog.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.dto.PersonType;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;

@RequiredArgsConstructor
class RegistrationService {
    private static final String USER = "USER";

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final CatalogApi catalogApi;

    void registerCommonUser(RegisterCommand registerCommand) {
        UserEntity userEntity = prepareCommonUserEntity(registerCommand);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        AddNewPersonCommand addNewPersonCommand = prepareAddNewPersonCommand(registerCommand, savedUserEntity.getId());

        catalogApi.addNewPerson(addNewPersonCommand);
    }

    private UserEntity prepareCommonUserEntity(RegisterCommand registerCommand) {
        //todo wyjatek
        UserRolesEntity rolesEntity = userRolesRepository.findByName(USER).get();

        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(registerCommand.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerCommand.getPassword()));
        userEntity.setRole(rolesEntity);

        return userEntity;
    }

    private AddNewPersonCommand prepareAddNewPersonCommand(RegisterCommand registerCommand, Long userId) {
        return AddNewPersonCommand
                .builder()
                .firstName(registerCommand.getFirstName())
                .lastName(registerCommand.getLastName())
                .birthDate(registerCommand.getBirthDate())
                .gender(registerCommand.getGender())
                .personType(PersonType.CLIENT)
                .userId(userId)
                .description(registerCommand.getDescription())
                .build();
    }
}