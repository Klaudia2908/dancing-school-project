package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("schoolConfiguration")
class Config {
    @Bean
    SchoolService schoolService(SchoolAddressRepository schoolAddressRepository, SchoolRepository schoolRepository) {
        return new SchoolService(schoolAddressRepository, schoolRepository);
    }
}
