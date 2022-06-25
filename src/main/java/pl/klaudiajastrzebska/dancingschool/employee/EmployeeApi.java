package pl.klaudiajastrzebska.dancingschool.employee;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeApi {
    private final CatalogApi catalogApi;

    public List<SchoolDto> getSchoolsForEmployee(String employeeUserName){
        return catalogApi.getSchoolsForEmployee(employeeUserName);
    }
}
