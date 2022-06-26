package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolAddressRepository;
import pl.klaudiajastrzebska.dancingschool.security.UserRepository;
import pl.klaudiajastrzebska.dancingschool.security.UserRolesRepository;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("personConfig")
class Config {
    @Bean
    EmployeeUserService personService(PersonRepository personRepository,
                                      PersonTypeRepository personTypeRepository,
                                      SchoolEmployeeRepository schoolEmployeeRepository,
                                      UserRepository userRepository,
                                      UserRolesRepository userRolesRepository,
                                      SchoolAddressRepository schoolAddressRepository,
                                      ValidationService validationService) {

        return new EmployeeUserService(validationService,
                personRepository,
                personTypeRepository,
                schoolEmployeeRepository,
                schoolAddressRepository,
                userRepository,
                userRolesRepository);
    }
}
