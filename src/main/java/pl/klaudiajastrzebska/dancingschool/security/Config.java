package pl.klaudiajastrzebska.dancingschool.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@Configuration("securityConfig")
class Config {

    @Bean
    PrincipalSecurityApi principalSecurityApi(SecurityService securityService, EmployeeUserService employeeUserService) {
        return new PrincipalSecurityApi(securityService, employeeUserService);
    }

    @Bean
    SecurityService securityService(UserRepository userRepository) {
        return new SecurityService(userRepository);
    }

    @Bean
    CustomAuthenticationProvider customAuthenticationProvider(SecurityService securityService, PasswordEncoder encoder) {
        return new CustomAuthenticationProvider(securityService, encoder);
    }

    @Bean
    RegistrationService registrationService(UserRepository userRepository,
                                            UserRolesRepository userRolesRepository,
                                            PasswordEncoder encoder,
                                            CatalogApi catalogApi,
                                            ValidationService validationService) {
        return new RegistrationService(userRepository, userRolesRepository, encoder, validationService, catalogApi);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
