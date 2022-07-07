package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonContactRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.PersonType;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonContactEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.ContactTypeEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserRolesEntity;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.util.List;

@RequiredArgsConstructor
class RegistrationService {
    private static final String USER = "USER";

    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final CatalogApi catalogApi;
    private final DictionaryService dictionaryService;
    private final PersonContactRepository personContactRepository;


    void registerCommonUser(RegisterCommand registerCommand) {
        validationService.validate(registerCommand);

        UserEntity userEntity = prepareCommonUserEntity(registerCommand);

        UserEntity savedUserEntity = userRepository.save(userEntity);

        AddNewPersonCommand addNewPersonCommand = prepareAddNewPersonCommand(registerCommand, savedUserEntity.getId());

        PersonEntity newPerson = catalogApi.addNewPerson(addNewPersonCommand);

        List<PersonContactEntity> personContactEntities = preparePersonContactEntities(newPerson, registerCommand);

        personContactRepository.saveAll(personContactEntities);
    }

    private List<PersonContactEntity> preparePersonContactEntities(PersonEntity newPerson, RegisterCommand registerCommand) {
        ContactTypeEntity email = dictionaryService.getContactTypeEntity("EMAIL");
        ContactTypeEntity tel = dictionaryService.getContactTypeEntity("TEL");


        PersonContactEntity emailPersonContact = new PersonContactEntity();
        emailPersonContact.setPerson(newPerson);
        emailPersonContact.setValue(registerCommand.getEmail());
        emailPersonContact.setContactType(email);

        PersonContactEntity phonePersonContact = new PersonContactEntity();
        phonePersonContact.setPerson(newPerson);
        phonePersonContact.setValue(registerCommand.getPhone());
        phonePersonContact.setContactType(tel);

        return List.of(emailPersonContact, phonePersonContact);
    }

    private UserEntity prepareCommonUserEntity(RegisterCommand registerCommand) {
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
                .description(null)
                .build();
    }
}
