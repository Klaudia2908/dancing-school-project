package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping(ApiUrlMappings.REGISTER_URL)
    String getRegistrationPagePage(Model model) {
        model.addAttribute(RegisterCommand.builder().build());
        return "security/register";
    }

    @PostMapping(ApiUrlMappings.REGISTER_URL)
    String postRegistrationForm(@ModelAttribute RegisterCommand registerCommand) {
        registrationService.registerCommonUser(registerCommand);

        return "security/login";
    }
}
