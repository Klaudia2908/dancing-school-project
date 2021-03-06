package pl.klaudiajastrzebska.dancingschool.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping(ApiUrlMappings.LOGIN_URL)
    String login() {
        return "security/login";
    }

    @GetMapping(ApiUrlMappings.LOGIN_ERROR_URL)
    String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "security/login";
    }
}
