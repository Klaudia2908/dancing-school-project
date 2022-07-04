package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.course.CourseRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.DayRepository;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("ScheduleConfiguration")
class Config {

    @Bean
    ScheduleService scheduleService(ScheduleRepository scheduleRepository, ValidationService validationService, PersonRepository personRepository, CourseRepository courseRepository, DayRepository dayRepository) {
        return new ScheduleService(scheduleRepository, validationService, personRepository, courseRepository, dayRepository);
    }
}
