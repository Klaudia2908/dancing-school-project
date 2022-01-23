package pl.klaudiajastrzebska.dancingschool.catalog.school.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SchoolDto {
    String name;
    String description;
    String city;
    String numberOfTheBuilding;
    String flatNumber;
    String postCode;
    String street;
    String shortName;
}
