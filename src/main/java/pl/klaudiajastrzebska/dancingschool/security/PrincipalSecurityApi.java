package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.person.EmployeeUserService;

import java.security.Principal;

@RequiredArgsConstructor
public class PrincipalSecurityApi {
    private static final String EMPLOYEE = "EMPLOYEE";

    private final SecurityService securityService;
    private final EmployeeUserService employeeUserService;

    public boolean principalAllowedForGivenSchoolResource(Principal principal, String schoolIdentifier) {
        if (principal == null) {
            return false;
        }

        String principalName = principal.getName();

        return employeeUserService.employeeExistsWithinSchoolAddress(schoolIdentifier, principalName);
    }

    public boolean principalAllowedForEmployeeResource(Principal principal) {
        if (principal == null) {
            return false;
        }

        String principalName = principal.getName();
        return EMPLOYEE.equalsIgnoreCase(securityService.getUser(principalName).getRole().getName());
    }
}
