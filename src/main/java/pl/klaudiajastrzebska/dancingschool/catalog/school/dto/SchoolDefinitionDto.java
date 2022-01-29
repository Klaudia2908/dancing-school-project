package pl.klaudiajastrzebska.dancingschool.catalog.school.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SchoolDefinitionDto {
    Long id;
    String name;
    String description;
}
