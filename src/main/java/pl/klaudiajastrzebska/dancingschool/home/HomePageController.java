package pl.klaudiajastrzebska.dancingschool.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.klaudiajastrzebska.dancingschool.security.ApiUrlMappings;

@Controller
class HomePageController {

    @GetMapping(ApiUrlMappings.HOME)
    String getHomePage() {
        return "home";
    }
}
