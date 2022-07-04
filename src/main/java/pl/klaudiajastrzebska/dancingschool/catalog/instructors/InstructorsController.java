package pl.klaudiajastrzebska.dancingschool.catalog.instructors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.UriUtils;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddCourseFormDataDto;
import pl.klaudiajastrzebska.dancingschool.catalog.instructors.dto.AddInstructorCommand;
import pl.klaudiajastrzebska.dancingschool.security.PrincipalSecurityApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
class InstructorsController {
    private final PrincipalSecurityApi principalSecurityApi;
    private final InstructorService instructorApi;

    @GetMapping("/{schoolIdentifier}/instructors")
    String getManageSchoolInstructorsView(@PathVariable String schoolIdentifier, Model model, Principal principal) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            log.error("Unauthorized access to school manage instructors section for principal " + principal.getName());

            return "error";
        }

        model.addAttribute("instructors", instructorApi.getInstructorsForSchool(schoolIdentifier));

        return "catalog/instructors/main";
    }

    @GetMapping("/{schoolIdentifier}/instructors/add")
    String getAddInstructorForm(@PathVariable String schoolIdentifier, Model model, Principal principal) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            log.error("Unauthorized access to school manage instructors section for principal " + principal.getName());

            return "error";
        }

        model.addAttribute("addInstructorCommand", AddInstructorCommand.builder().build());

        return "catalog/instructors/add";
    }

    @PostMapping("/{schoolIdentifier}/instructors/add")
    String postInstructorsFormData(@PathVariable String schoolIdentifier, @ModelAttribute AddInstructorCommand command, Model model, Principal principal) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            log.error("Unauthorized access to school manage instructors section for principal " + principal.getName());

            return "error";
        }

        instructorApi.addInstructor(command, schoolIdentifier);

        return "redirect:/" + schoolIdentifier + "/instructors";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        AddInstructorCommand validatedCommand = (AddInstructorCommand) e.getValidatedCommand();
        String schoolIdentifier = UriUtils.getPathVariable("schoolIdentifier", request);

        model.addAttribute("addInstructorCommand", validatedCommand);
        model.addAttribute("schoolIdentifier", schoolIdentifier);
        model.addAttribute("validationErrors", e.getValidationErrors());

        model.addAttribute("chosenGender", validatedCommand.getGender());

        return "catalog/instructors/add";
    }
}
