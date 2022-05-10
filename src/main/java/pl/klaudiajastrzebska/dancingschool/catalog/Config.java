package pl.klaudiajastrzebska.dancingschool.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonService;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolService;
import pl.klaudiajastrzebska.dancingschool.security.SecurityService;

@Configuration("catalogConfiguration")
class Config {

    @Bean
    CatalogApi catalogApi(PersonService personService, SchoolService schoolService, SecurityService securityService) {
        return new CatalogApi(personService, securityService, schoolService);
    }
}
