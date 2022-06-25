package pl.klaudiajastrzebska.dancingschool.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.klaudiajastrzebska.dancingschool.security.PrincipalSecurityApi;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/my-schools")
class EmployeeController {
    private final EmployeeApi employeeApi;
    private final PrincipalSecurityApi principalSecurityApi;

    @GetMapping
    String getMySchoolsPage(Model model, Principal principal) {
        if (!principalSecurityApi.principalAllowedForEmployeeResource(principal)) {
            log.error("=================== Not allowed access for employee schools list view ===================");
            return "/error";
        }

        model.addAttribute("schools", employeeApi.getSchoolsForEmployee(principal.getName()));

        return "/catalog/schools";
    }
}
