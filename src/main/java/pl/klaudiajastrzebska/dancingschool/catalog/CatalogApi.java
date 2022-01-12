package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.dto.AddNewPersonCommand;

@RequiredArgsConstructor
public class CatalogApi {
    private final PersonService personService;

    public void addNewPerson(AddNewPersonCommand command) {
        personService.addNewPerson(command);
    }
}
