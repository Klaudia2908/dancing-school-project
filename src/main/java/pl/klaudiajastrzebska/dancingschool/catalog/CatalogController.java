package pl.klaudiajastrzebska.dancingschool.catalog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.klaudiajastrzebska.dancingschool.catalog.course.CourseService;
import pl.klaudiajastrzebska.dancingschool.catalog.instructors.InstructorService;
import pl.klaudiajastrzebska.dancingschool.security.ApiUrlMappings;

import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
class  CatalogController {
    private final CatalogApi catalogApi;
    private final InstructorService instructorService;
    private final CourseService courseService;

    @GetMapping(ApiUrlMappings.SCHOOLS_BROWSE)
    String schoolsView(@RequestParam(value = "city") String city, Model model) {
        model.addAttribute("schools", catalogApi.getSchoolsByCity(city));

        return "/catalog/schools";
    }

    @GetMapping(ApiUrlMappings.SINGLE_SCHOOL_SCREEN)
    String schoolInfo(@PathVariable String schoolIdentifier, Model model, Principal principal) {
        model.addAttribute("school", catalogApi.getSchoolByIdentifier(schoolIdentifier));
        model.addAttribute("courses", courseService.getAllCoursesForSchoolIdentifier(schoolIdentifier));
        model.addAttribute("instructors", instructorService.getInstructorsForSchool(schoolIdentifier));

        if(Objects.nonNull(principal)){
            model.addAttribute("employeeAllowedToAddSchedule", catalogApi.isUserAllowedToAddSchedule(schoolIdentifier, principal.getName()));
        }

        return "/catalog/school-info";
    }
}
