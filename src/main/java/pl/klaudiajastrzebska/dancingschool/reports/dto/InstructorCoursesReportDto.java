package pl.klaudiajastrzebska.dancingschool.reports.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class InstructorCoursesReportDto {
    String instructorFirstName;
    String instructorLastName;
    String courseName;
    String schoolName;
    String city;
    String schoolIdentifier;
    LocalDate startDate;
    LocalDate endDate;
    String hourFrom;
    String hourTo;
    String dayOfWeek;
}
