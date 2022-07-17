package pl.klaudiajastrzebska.dancingschool.reports;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.reports.dto.InstructorCoursesReportDto;
import pl.klaudiajastrzebska.dancingschool.reports.dto.SignedPeopleReportDto;
import pl.klaudiajastrzebska.dancingschool.reports.entity.ViewInstructorCoursesEntity;
import pl.klaudiajastrzebska.dancingschool.reports.entity.ViewSavedPeopleEntity;

import java.util.List;

@RequiredArgsConstructor
public class ReportsService {
    private final ViewSavedPeopleRepository viewSavedPeopleRepository;
    private final InstructorCoursesReportRepository instructorCoursesReportRepository;
    private final EmployeeUserService employeeUserService;
    
    public List<SignedPeopleReportDto> prepareSignedPeopleReport(String principalUserName) {
        List<String> schoolIdentifiersForEmployee = employeeUserService.getSchoolsForEmployee(principalUserName)
                .stream()
                .map(SchoolDto::getShortName)
                .toList();

       return viewSavedPeopleRepository.findBySchoolIdentifierInOrderByStartDate(schoolIdentifiersForEmployee)
               .stream()
               .map(ViewSavedPeopleEntity::toDto)
               .toList();
    }

    public List<InstructorCoursesReportDto> prepareInstructorsCoursesReport(String principalUserName) {
        List<String> schoolIdentifiersForEmployee = employeeUserService.getSchoolsForEmployee(principalUserName)
                .stream()
                .map(SchoolDto::getShortName)
                .toList();

        return instructorCoursesReportRepository.findAllBySchoolIdentifiers(schoolIdentifiersForEmployee)
                .stream()
                .map(ViewInstructorCoursesEntity::toDto)
                .toList();
    }
}
