package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import java.util.List;

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

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model) {
        model.addAttribute("registerError", true);
        model.addAttribute("validationErrors", e.getValidationErrors());
        model.addAttribute(e.getValidatedCommand());

        return "security/register";
    }
}
