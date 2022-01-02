package pl.klaudiajastrzebska.dancingschool.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomePageController {

    @GetMapping("/home")
    String getHomePage() {
        return "home";
    }
}
