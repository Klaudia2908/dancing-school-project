package pl.klaudiajastrzebska.dancingschool.reports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;

@Configuration("reportsConfiguration")
class Config {

    @Bean
    ReportsService reportsService(EmployeeUserService employeeUserService, ViewSavedPeopleRepository viewSavedPeopleRepository, InstructorCoursesReportRepository instructorCoursesReportRepository){
        return new ReportsService(viewSavedPeopleRepository, instructorCoursesReportRepository, employeeUserService);
    }
}
