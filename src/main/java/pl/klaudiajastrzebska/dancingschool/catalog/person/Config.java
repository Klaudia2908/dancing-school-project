package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("personConfig")
class Config {
    @Bean
    PersonService personService(PersonRepository personRepository, PersonTypeRepository personTypeRepository){
        return new PersonService(personRepository, personTypeRepository);
    }
}
