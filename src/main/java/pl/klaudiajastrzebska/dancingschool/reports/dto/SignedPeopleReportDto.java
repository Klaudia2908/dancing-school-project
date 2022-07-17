package pl.klaudiajastrzebska.dancingschool.reports.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class SignedPeopleReportDto {
    String courseName;
    String schoolName;
    String schoolCity;
    String schoolIdentifier;
    LocalDate startDate;
    LocalDate endDate;
    String hourFrom;
    String hourTo;
    String dayOfWeek;
    String savedPeopleCount;
    String maxCapacity;
    String registrationStatus;
}
