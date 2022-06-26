package pl.klaudiajastrzebska.dancingschool.catalog.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.UriUtils;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddCourseFormDataDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddNewCourseCommand;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.security.PrincipalSecurityApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
class CourseController {
    private final PrincipalSecurityApi principalSecurityApi;
    private final CourseService courseApi;
    private final DictionaryService dictionaryService;

    @GetMapping("/{schoolIdentifier}/courses")
    String getManageCoursesMainScreen(@PathVariable String schoolIdentifier, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        model.addAttribute("courses", courseApi.getAllCoursesForSchoolIdentifier(schoolIdentifier));

        return "/catalog/courses/main";
    }

    @GetMapping("/{schoolIdentifier}/courses/add")
    String getAddNewCourseScreen(@PathVariable String schoolIdentifier, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        model.addAttribute("addCourseFormDataDto", AddCourseFormDataDto.builder().build());
        model.addAttribute("styles", dictionaryService.getDanceStyles());
        model.addAttribute("levels", dictionaryService.getDanceLevels());
        model.addAttribute("ageGroups", dictionaryService.getAgeGroups());

        return "/catalog/courses/add";
    }

    @PostMapping("/{schoolIdentifier}/courses/add")
    String postAddNewCourseFormData(@PathVariable String schoolIdentifier, @ModelAttribute AddCourseFormDataDto addCourseFormDataDto, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        courseApi.addNewCourse(AddNewCourseCommand.of(addCourseFormDataDto, schoolIdentifier));
        model.addAttribute("courses", courseApi.getAllCoursesForSchoolIdentifier(schoolIdentifier));

        return "/catalog/courses/main";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        AddCourseFormDataDto validatedCommand = (AddCourseFormDataDto) e.getValidatedCommand();

        model.addAttribute("addCourseFormDataDto", validatedCommand);
        model.addAttribute("schoolIdentifier", UriUtils.getPathVariable("schoolIdentifier", request));
        model.addAttribute("validationErrors", e.getValidationErrors());

        model.addAttribute("chosenStyle", validatedCommand.getStyle());
        model.addAttribute("chosenLevel", validatedCommand.getLevel());
        model.addAttribute("chosenAgeGroup", validatedCommand.getAgeGroup());

        model.addAttribute("styles", dictionaryService.getDanceStyles());
        model.addAttribute("levels", dictionaryService.getDanceLevels());
        model.addAttribute("ageGroups", dictionaryService.getAgeGroups());

        return "/catalog/courses/add";
    }
}
