package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto;

import lombok.Builder;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
@Builder
public class AddScheduleFormData {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = ValidationMessages.NOT_NULL)
    LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = ValidationMessages.NOT_NULL)
    LocalDate dateTo;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String dayOfWeek;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String timeStart;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String timeEnd;

    @NotNull(message = ValidationMessages.NOT_NULL)
    Long instructorId;

    @NotNull(message = ValidationMessages.NOT_NULL)
    Long maxCapacity;
}