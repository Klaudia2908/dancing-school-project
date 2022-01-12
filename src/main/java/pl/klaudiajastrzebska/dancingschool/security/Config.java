package pl.klaudiajastrzebska.dancingschool.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;

@Configuration("securityConfig")
class Config {

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
                                            CatalogApi catalogApi){
        return new RegistrationService(userRepository, userRolesRepository, encoder, catalogApi);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
