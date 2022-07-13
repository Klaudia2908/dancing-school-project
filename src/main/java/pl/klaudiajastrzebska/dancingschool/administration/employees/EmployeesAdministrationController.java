package pl.klaudiajastrzebska.dancingschool.administration.employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.klaudiajastrzebska.dancingschool.administration.employees.dto.CreateEmployeeRequest;
import pl.klaudiajastrzebska.dancingschool.catalog.CatalogApi;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/administration")
class EmployeesAdministrationController {
    private final CatalogApi catalogApi;

    @GetMapping("/employees")
    String getEmployeesAdministrationView(Model model) {
        model.addAttribute("employees", catalogApi.getAllEmployees());
        return "/administration/employees/main";
    }

    @GetMapping("/employees/new")
    String getCreateEmployeeView(Model model) {
        model.addAttribute("createEmployeeRequest", CreateEmployeeRequest.builder().build());

        return "/administration/employees/new";
    }

    @GetMapping("/employees/attach")
    String getAttachSchoolToEmployeeScreen(Model model) {
        model.addAttribute("employeesWithoutAttachedSchool", catalogApi.getAllEmployeesWithoutAttachedSchool());
        model.addAttribute("employeesWithSchools", catalogApi.getAllEmployees());

        return "/administration/employees/attach";
    }

    @GetMapping("employees/delete/{employeeLogin}/{schoolIdentifier}")
    String deleteEmployee(@PathVariable String employeeLogin, @PathVariable String schoolIdentifier, Model model){
        catalogApi.deleteEmployee(employeeLogin, schoolIdentifier);

        return "redirect:/administration/employees";
    }

    @GetMapping("/employees/attach/{employeeLogin}")
    String getAttachSchoolToSpecificEmployeeScreen(Model model, @PathVariable String employeeLogin) {
        model.addAttribute("login", employeeLogin);
        model.addAttribute("availableSchools", catalogApi.getNotAttachedSchoolsForLogin(employeeLogin));

        return "/administration/employees/schools";
    }

    @GetMapping("/employees/attach/{employeeLogin}/{schoolIdentifier}")
    String attachEmployeeToSchool(Model model, @PathVariable String employeeLogin, @PathVariable String schoolIdentifier) {
        catalogApi.attachEmployeeToSchool(employeeLogin, schoolIdentifier);

        return "redirect:/administration/employees/attach";
    }

    @PostMapping("/employees/new")
    String createEmployee(@ModelAttribute CreateEmployeeRequest request, Model model) {
        model.addAttribute("addedSuccessfully", true);
        catalogApi.createNewEmployee(request);

        return "redirect:/administration/employees/attach";
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    String handleValidationException(ValidationException e, Model model, HttpServletRequest request) {
        model.addAttribute("createEmployeeRequest", e.getValidatedCommand());
        model.addAttribute("validationErrors", e.getValidationErrors());

        return "administration/employees/new";
    }
}
