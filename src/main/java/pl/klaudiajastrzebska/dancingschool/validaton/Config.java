package pl.klaudiajastrzebska.dancingschool.validaton;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.ValidatorFactory;

@Configuration("validationConfiguration")
class Config {

    @Bean
    ValidationService validationService(ValidatorFactory validatorFactory){
        return new ValidationService(validatorFactory);
    }
}
