package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
class CatalogController {
    private final CatalogApi  catalogApi;

    @GetMapping("/schools")
    String schoolsView(@RequestParam(value = "city") String city, Model model){
        model.addAttribute("schools", catalogApi.getSchoolsByCity(city));

        return "/catalog/schools";
    }
}
