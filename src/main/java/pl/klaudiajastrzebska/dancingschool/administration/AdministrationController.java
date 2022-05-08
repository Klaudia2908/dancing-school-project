package pl.klaudiajastrzebska.dancingschool.administration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.EditSchoolDataCommand;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class AdministrationController {

    @GetMapping
    String getAdministrationMainPage(Model model) {
        return "administration/main";
    }
}
