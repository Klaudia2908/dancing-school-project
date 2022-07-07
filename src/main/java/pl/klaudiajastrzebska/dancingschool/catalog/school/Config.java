package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("schoolConfiguration")
class Config {
    @Bean
    SchoolService schoolService(SchoolAddressRepository schoolAddressRepository, SchoolContactRepository schoolContactRepository, SchoolRepository schoolRepository, ValidationService validationService, DictionaryService dictionaryService) {
        return new SchoolService(schoolAddressRepository, schoolContactRepository, schoolRepository, validationService, dictionaryService);
    }
}
