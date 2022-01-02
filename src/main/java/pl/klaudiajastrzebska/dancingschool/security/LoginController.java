package pl.klaudiajastrzebska.dancingschool.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    String login() {
        return "security/login";
    }

    @GetMapping("/login-error")
    String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "security/login";
    }
}
