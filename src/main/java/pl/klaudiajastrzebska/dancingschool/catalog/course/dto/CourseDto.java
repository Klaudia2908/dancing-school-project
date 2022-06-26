package pl.klaudiajastrzebska.dancingschool.catalog.course.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CourseDto {
    String name;
    String description;
    BigDecimal price;
    String level;
    String style;
    String ageGroup;
    String address;
}


