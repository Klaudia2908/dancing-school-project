package pl.klaudiajastrzebska.dancingschool.administration.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.UriUtils;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddAddressToExistiongSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class SchoolAddressAdministrationController {
    private static final String ALL_SCHOOLS_STRING = "";
    private final CatalogApi catalogApi;

    @GetMapping("/schools/add/existing")
    String getAddAddressToExistingSchoolPage(Model model) {
        model.addAttribute("schools", catalogApi.getSchoolDefinitions());

        return "administration/school/add-existing-school";
    }

    @GetMapping("/schools/add/existing/{schoolId}")
    String getAddAddressToExistingSchoolPage(@PathVariable Long schoolId, Model model) {
        model.addAttribute("schoolId", schoolId);
        model.addAttribute("addAddressToExistingSchoolCommand", AddAddressToExistiongSchoolCommand.builder().build());

        return "administration/school/add-address-to-existing-school";
    }

    @PostMapping("/schools/add/existing/{id}")
    String postAddAddressToExistingSchoolPage(@ModelAttribute AddAddressToExistiongSchoolCommand command, @PathVariable Long id, Model model) {
        catalogApi.addNewAddressToExistingSchool(id, command);
        model.addAttribute("schools", catalogApi.getSchoolsByCity(ALL_SCHOOLS_STRING));
        model.addAttribute("e", true);

        return "administration/school/add-school";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        model.addAttribute("addAddressToExistingSchoolCommand", e.getValidatedCommand());
        model.addAttribute("schoolId", UriUtils.getPathVariable("id", request));
        model.addAttribute("validationErrors", e.getValidationErrors());

        return "administration/school/add-address-to-existing-school";
    }
}
