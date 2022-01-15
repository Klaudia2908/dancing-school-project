package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("schoolConfiguration")
class Config {
    @Bean
    SchoolService schoolService(SchoolRepository repository) {
        return new SchoolService(repository);
    }
}
