package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klaudiajastrzebska.dancingschool.security.ApiUrlMappings;

@Controller
@RequiredArgsConstructor
class CatalogController {
    private final CatalogApi catalogApi;

    @GetMapping(ApiUrlMappings.SCHOOLS_BROWSE)
    String schoolsView(@RequestParam(value = "city") String city, Model model) {
        model.addAttribute("schools", catalogApi.getSchoolsByCity(city));

        return "/catalog/schools";
    }

    @GetMapping(ApiUrlMappings.SINGLE_SCHOOL_SCREEN)
    String schoolInfo(@PathVariable String schoolIdentifier, Model model) {
        model.addAttribute("school", catalogApi.getSchoolByIdentifier(schoolIdentifier));

        return "/catalog/school-info";
    }

}
