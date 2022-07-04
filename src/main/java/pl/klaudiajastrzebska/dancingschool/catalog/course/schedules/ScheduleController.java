package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.UriUtils;
import pl.klaudiajastrzebska.dancingschool.catalog.course.CourseService;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddCourseFormDataDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleFormData;
import pl.klaudiajastrzebska.dancingschool.catalog.instructors.InstructorService;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.security.PrincipalSecurityApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
class ScheduleController {
    private final ScheduleService scheduleService;
    private final CourseService courseService;
    private final PrincipalSecurityApi principalSecurityApi;
    private final DictionaryService dictionaryService;
    private final InstructorService instructorService;

    @GetMapping("/{schoolIdentifier}/schedules/{courseUUID}")
    String getMainScheduleViewForSchoolAndCourse(@PathVariable String schoolIdentifier, @PathVariable String courseUUID, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        model.addAttribute("courseName", courseService.getCourseNameByUuid(courseUUID));
        model.addAttribute("schedules", scheduleService.getAllSchedulesForSchoolAndCourse(schoolIdentifier, courseUUID));

        return "catalog/courses/schedules/main";
    }

    @GetMapping("/{schoolIdentifier}/schedules/{courseUUID}/add")
    String getMainAddScheduleViewForSchoolAndCourse(@PathVariable String schoolIdentifier, @PathVariable String courseUUID, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        model.addAttribute("addScheduleFormData", AddScheduleFormData.builder().build());
        model.addAttribute("daysOfWeek", dictionaryService.getDays());
        model.addAttribute("instructors", instructorService.getInstructorsForSchool(schoolIdentifier));

        return "catalog/courses/schedules/add";
    }

    @PostMapping("/{schoolIdentifier}/schedules/{courseUUID}/add")
    String postMainAddScheduleFormForSchoolAndCourse(@PathVariable String schoolIdentifier, @PathVariable String courseUUID, @ModelAttribute AddScheduleFormData addScheduleFormData, Principal principal, Model model) {
        if (!principalSecurityApi.principalAllowedForGivenSchoolResource(principal, schoolIdentifier)) {
            return "/error";
        }

        AddScheduleCommand command = AddScheduleCommand
                .builder()
                .addScheduleFormData(addScheduleFormData)
                .principalUserName(principal.getName())
                .schoolIdentifier(schoolIdentifier)
                .courseUUID(courseUUID)
                .build();

        scheduleService.addScheduleForSchoolAndPrincipal(command);

        model.addAttribute("courseName", courseService.getCourseNameByUuid(courseUUID));
        model.addAttribute("schedules", scheduleService.getAllSchedulesForSchoolAndCourse(schoolIdentifier, courseUUID));

        return "catalog/courses/schedules/main";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        AddScheduleFormData validatedCommand = (AddScheduleFormData) e.getValidatedCommand();
        String schoolIdentifier = UriUtils.getPathVariable("schoolIdentifier", request);

        model.addAttribute("addScheduleFormData", validatedCommand);
        model.addAttribute("schoolIdentifier", schoolIdentifier);
        model.addAttribute("validationErrors", e.getValidationErrors());


        model.addAttribute("daysOfWeek", dictionaryService.getDays());
        model.addAttribute("instructors", instructorService.getInstructorsForSchool(schoolIdentifier));
        model.addAttribute("chosenDayOfWeek", validatedCommand.getDayOfWeek());

        return "catalog/courses/schedules/add";
    }
}
