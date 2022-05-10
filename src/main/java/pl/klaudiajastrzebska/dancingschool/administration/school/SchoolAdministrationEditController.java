package pl.klaudiajastrzebska.dancingschool.administration.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.UriUtils;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.EditSchoolDataCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class SchoolAdministrationEditController {
    private static final String ALL_SCHOOLS_STRING = "";
    private final CatalogApi catalogApi;

    @GetMapping("/schools/edit/{schoolIdentifier}")
    String getEditSchoolScreen(Model model, @PathVariable String schoolIdentifier) {
        model.addAttribute("editSchoolDataCommand", prepareEditSchoolDataCommandForOnScreenPresentation(schoolIdentifier));

        return "administration/school/edit-school";
    }

    @PostMapping("/schools/edit/{schoolIdentifier}")
    String postEditSchoolDataForm(@ModelAttribute EditSchoolDataCommand editSchoolDataCommand, @PathVariable String schoolIdentifier, Model model) {
        catalogApi.editSchoolData(editSchoolDataCommand, schoolIdentifier);
        model.addAttribute("editedSuccesfully", true);

        return "redirect:/administration/schools";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        model.addAttribute("editSchoolDataCommand", e.getValidatedCommand());
        model.addAttribute("schoolIdentifier", UriUtils.getPathVariable("schoolIdentifier", request));
        model.addAttribute("validationErrors", e.getValidationErrors());

        return "administration/school/edit-school";
    }

    private EditSchoolDataCommand prepareEditSchoolDataCommandForOnScreenPresentation(String schoolIdentifier) {
        SchoolDto school = catalogApi.getSchoolByIdentifier(schoolIdentifier);

        return EditSchoolDataCommand
                .builder()
                .name(school.getName())
                .description(school.getDescription())
                .city(school.getCity())
                .flatNumber(school.getFlatNumber())
                .numberOfTheBuilding(school.getNumberOfTheBuilding())
                .postCode(school.getPostCode())
                .shortName(school.getShortName())
                .street(school.getStreet())
                .build();
    }
}
