package pl.klaudiajastrzebska.dancingschool.administration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.administration.dto.AddAddressToExistiongSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.administration.dto.AddNewSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class AdministrationController {
    public static final String ALL_SCHOOLS_STRING = "";
    private final CatalogApi catalogApi;

    @GetMapping
    String getAdministrationMainPage() {
        return "administration/main";
    }

    @GetMapping("/schools")
    String getSchoolsAdministrationPage(Model model) {
        model.addAttribute("schools", catalogApi.getSchoolsByCity(ALL_SCHOOLS_STRING));

        return "administration/schools";
    }

    @GetMapping("/schools/add")
    String getAddSchoolPage() {
        return "administration/add-school";
    }


    @GetMapping("/schools/add/new")
    String getAddNewSchoolPage(Model model) {
        model.addAttribute("addNewSchoolCommand", AddNewSchoolCommand.builder().build());

        return "administration/add-new-school";
    }

    @PostMapping("/schools/add/new")
    String postAddNewSchoolPage(@ModelAttribute AddNewSchoolCommand addNewSchoolCommand, Model model) {
        catalogApi.addNewSchool(addNewSchoolCommand);

        model.addAttribute("addedSuccesfully", true);

        return "administration/add-school";
    }

    @GetMapping("/schools/add/existing")
    String getAddAddressToExistingSchoolPage(Model model) {
        model.addAttribute("schools", catalogApi.getSchoolDefinitions());

        return "administration/add-existing-school";
    }

    @GetMapping("/schools/add/existing/{schoolId}")
    String getAddAddressToExistingSchoolPage(@PathVariable Long schoolId, Model model) {
        model.addAttribute("id", schoolId);
        model.addAttribute("addAddressToExistingSchoolCommand", AddAddressToExistiongSchoolCommand.builder().build());

        return "administration/add-address-to-existing-school";
    }

    @PostMapping("/schools/add/existing/{id}")
    String postAddAddressToExistingSchoolPage(@ModelAttribute AddAddressToExistiongSchoolCommand command, @PathVariable String id, Model model) {
//        catalogApi.addNewSchool(addNewSchoolCommand);

        model.addAttribute("addedSuccesfully", true);

        return "administration/add-school";
    }

    @GetMapping("/schools/delete/{schoolIdentifier}")
    String performDeleteSchool(@PathVariable String schoolIdentifier, Model model) {
        catalogApi.deleteSchoolByShortName(schoolIdentifier);
        model.addAttribute("schools", catalogApi.getSchoolsByCity(ALL_SCHOOLS_STRING));

        return "redirect:/administration/schools";
    }
}
