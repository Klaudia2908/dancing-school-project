package pl.klaudiajastrzebska.dancingschool.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;

@Configuration("EmployeeConfiguration")
class Config {

    @Bean
    EmployeeApi employeeApi(CatalogApi catalogApi) {
        return new EmployeeApi(catalogApi);
    }
}
