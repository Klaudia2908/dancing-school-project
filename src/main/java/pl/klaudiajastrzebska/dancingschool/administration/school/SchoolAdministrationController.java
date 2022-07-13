package pl.klaudiajastrzebska.dancingschool.administration.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddNewSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class SchoolAdministrationController {
    public static final String ALL_SCHOOLS_STRING = "";
    private final CatalogApi catalogApi;

    @GetMapping("/schools")
    String getSchoolsAdministrationPage(Model model) {
        model.addAttribute("schools", catalogApi.getSchoolsByCity(ALL_SCHOOLS_STRING));

        return "administration/school/schools";
    }

    @GetMapping("/schools/add")
    String getAddSchoolPage() {
        return "administration/school/add-school";
    }


    @GetMapping("/schools/add/new")
    String getAddNewSchoolPage(Model model) {
        model.addAttribute("addNewSchoolCommand", AddNewSchoolCommand.builder().build());

        return "administration/school/add-new-school";
    }

    @PostMapping("/schools/add/new")
    String postAddNewSchoolPage(@ModelAttribute AddNewSchoolCommand addNewSchoolCommand, Model model) {
        catalogApi.addNewSchool(addNewSchoolCommand);

        model.addAttribute("addedSuccesfully", true);

        return "administration/school/add-school";
    }

    @GetMapping("/schools/delete/{schoolIdentifier}")
    String performDeleteSchool(@PathVariable String schoolIdentifier, Model model) {
        catalogApi.deleteSchoolByShortName(schoolIdentifier);
        model.addAttribute("schools", catalogApi.getSchoolsByCity(ALL_SCHOOLS_STRING));

        return "redirect:/administration/schools";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        model.addAttribute("addNewSchoolCommand", e.getValidatedCommand());
        model.addAttribute("validationErrors", e.getValidationErrors());

        return "administration/school/add-new-school";
    }
}
