package pl.klaudiajastrzebska.dancingschool.administration.school.dto;

import lombok.Builder;
import lombok.Value;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationMessages;

import javax.validation.constraints.NotBlank;

@Value
@Builder
public class AddAddressToExistiongSchoolCommand {
    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String street;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String numberOfTheBuilding;

    String flatNumber;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String city;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String postCode;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    String shortName;
}
