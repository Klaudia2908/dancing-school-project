package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("schoolConfiguration")
class Config {
    @Bean
    SchoolService schoolService(SchoolAddressRepository schoolAddressRepository, SchoolRepository schoolRepository, ValidationService validationService) {
        return new SchoolService(schoolAddressRepository, schoolRepository, validationService);
    }
}
