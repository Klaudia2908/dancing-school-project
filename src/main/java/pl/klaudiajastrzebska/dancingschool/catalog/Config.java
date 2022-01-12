package pl.klaudiajastrzebska.dancingschool.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("catalogConfiguration")
class Config {

    @Bean
    CatalogApi catalogApi(PersonRepository personRepository, PersonTypeRepository personTypeRepository) {
        PersonService personService = new PersonService(personRepository, personTypeRepository);

        return new CatalogApi(personService);
    }
}
