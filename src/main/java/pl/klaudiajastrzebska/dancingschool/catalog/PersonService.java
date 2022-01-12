package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.entity.PersonTypeEntity;

import java.util.Optional;

@RequiredArgsConstructor
class PersonService {
    private final PersonRepository personRepository;
    private final PersonTypeRepository personTypeRepository;

    void addNewPerson(AddNewPersonCommand command) {
        PersonEntity personEntity = preparePersonEntity(command);

        personRepository.save(personEntity);
    }

    private PersonEntity preparePersonEntity(AddNewPersonCommand command) {
        // todo wyjatek dla braku wartosci slownikowych i exception handler na /errorpage
        Optional<PersonTypeEntity> value = personTypeRepository.findByValue(command.getPersonType().name());

        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName(command.getFirstName());
        personEntity.setLastName(command.getLastName());
        personEntity.setGender(command.getGender());
        personEntity.setBirthDate(command.getBirthDate());
        personEntity.setDescription(command.getDescription());
        personEntity.setUserId(command.getUserId());
        personEntity.setPersonType(value.get());

        return personEntity;
    }
}
