package pl.klaudiajastrzebska.dancingschool.administration.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddNewSchoolCommand {
    String name;
    String description;
}
