package pl.klaudiajastrzebska.dancingschool.catalog.course.dto;

import lombok.Builder;
import lombok.Value;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
@Builder
public class AddCourseFormDataDto {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String name;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String description;

    @NotNull(message = ValidationMessages.NOT_NULL)
    BigDecimal price;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String style;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String level;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String ageGroup;
}
