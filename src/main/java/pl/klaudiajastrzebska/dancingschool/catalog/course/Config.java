package pl.klaudiajastrzebska.dancingschool.catalog.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("CourseConfiguration")
class Config {

    @Bean
    CourseService courseService(ValidationService validationService) {
        return new CourseService(validationService);
    }
}
