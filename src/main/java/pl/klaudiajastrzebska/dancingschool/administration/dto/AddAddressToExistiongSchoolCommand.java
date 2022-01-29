package pl.klaudiajastrzebska.dancingschool.administration.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddAddressToExistiongSchoolCommand {
    String street;
    String numberOfTheBuilding;
    String flatNumber;
    String city;
    String postCode;
    String shortName;
}
