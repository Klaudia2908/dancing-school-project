package pl.klaudiajastrzebska.dancingschool.catalog.instructors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("InstructorConfiguration")
class Config {
    @Bean
    InstructorService instructorService(EmployeeUserService employeeUserService, ValidationService validationService) {
        return new InstructorService(employeeUserService, validationService);
    }
}
