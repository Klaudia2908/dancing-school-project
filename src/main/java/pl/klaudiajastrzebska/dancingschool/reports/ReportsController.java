package pl.klaudiajastrzebska.dancingschool.reports;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.klaudiajastrzebska.dancingschool.reports.dto.InstructorCoursesReportDto;
import pl.klaudiajastrzebska.dancingschool.reports.dto.SignedPeopleReportDto;
import pl.klaudiajastrzebska.dancingschool.security.PrincipalSecurityApi;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
class ReportsController {
    private final ReportsService reportsService;
    private final PrincipalSecurityApi principalSecurityApi;

    @GetMapping("/reports")
    String getChooseReportMainPage(Principal principal) {
        if(!principalSecurityApi.principalAllowedForEmployeeResource(principal)){
            return "error";
        }
        return "reports/main";
    }

    @GetMapping("/reports/signed")
    String getSignedPeopleReportScreen(Principal principal, Model model) {
        if(!principalSecurityApi.principalAllowedForEmployeeResource(principal)){
            return "error";
        }

        List<SignedPeopleReportDto> reportData = reportsService.prepareSignedPeopleReport(principal.getName());
        model.addAttribute("signedPeopleReportData", reportData);

        return "reports/signed-people";
    }

    @GetMapping("/reports/instructor-courses")
    String getInstructorCoursesReportsScreen(Principal principal, Model model) {
        if(!principalSecurityApi.principalAllowedForEmployeeResource(principal)){
            return "error";
        }

        List<InstructorCoursesReportDto> reportData = reportsService.prepareInstructorsCoursesReport(principal.getName());
        model.addAttribute("instructorCoursesReportData", reportData);

        return "reports/instructor-courses";
    }
}
