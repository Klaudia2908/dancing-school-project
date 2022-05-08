package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddAddressToExistiongSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddNewSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.EditSchoolDataCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonService;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.AddNewPersonCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolService;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDefinitionDto;
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

    public List<SchoolDefinitionDto> getSchoolDefinitions() {
        return schoolService.getSchoolDefinitions();
    }

    public SchoolDto getSchoolByIdentifier(String schoolIdentifier) {
        return schoolService.getSchoolByIdentifier(schoolIdentifier);
    }

    public void addNewSchool(AddNewSchoolCommand command) {
        schoolService.addNewSchool(command);
    }

    public void addNewAddressToExistingSchool(Long schoolId, AddAddressToExistiongSchoolCommand command) {
        schoolService.addAddressToExistingSchoolId(schoolId, command);
    }

    public void deleteSchoolByShortName(String schoolShortName) {
        schoolService.deleteSchoolByShortName(schoolShortName);
    }

    public void editSchoolData(EditSchoolDataCommand editSchoolDataCommand, String schoolIdentifier) {
        schoolService.editSchoolData(editSchoolDataCommand, schoolIdentifier);
    }
}
