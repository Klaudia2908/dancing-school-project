package pl.klaudiajastrzebska.dancingschool.catalog.course;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolAddressRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("CourseConfiguration")
class Config {

    @Bean
    CourseService courseService(ValidationService validationService, SchoolAddressRepository schoolAddressRepository, DictionaryService dictionaryService, CourseRepository courseRepository) {
        return new CourseService(schoolAddressRepository, courseRepository, validationService, dictionaryService);
    }
}
