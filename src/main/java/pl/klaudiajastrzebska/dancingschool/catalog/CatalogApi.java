package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonService;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolService;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;

import java.util.List;

@RequiredArgsConstructor
public class CatalogApi {
    private final PersonService personService;
    private final SchoolService schoolService;

    public void addNewPerson(AddNewPersonCommand command) {
        personService.addNewPerson(command);
    }

    public List<SchoolDto> getSchoolsByCity(String city) {
        return schoolService.getSchoolsByCity(city);
    }

    public SchoolDto getSchoolByIdentifier(String schoolIdentifier) {
        return schoolService.getSchoolByIdentifier(schoolIdentifier);
    }
}
