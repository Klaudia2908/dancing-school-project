package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ScheduleDto {
    String dayOfWeek;
    String hourFrom;
    String hourTo;
    LocalDate startDate;
    LocalDate endDate;
    long maxCapacity;
    String instructorName;
    String uuid;


}
