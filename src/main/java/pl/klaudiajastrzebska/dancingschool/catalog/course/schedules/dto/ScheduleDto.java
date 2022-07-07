package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDto {
    String dayOfWeek;
    String hourFrom;
    String hourTo;
    LocalDate startDate;
    LocalDate endDate;
    long maxCapacity;
    String instructorName;
    String uuid;
    boolean isCurrentUserSignedToThatSchedule;
    long takenPlaces;
    boolean placesAvailable;
}
