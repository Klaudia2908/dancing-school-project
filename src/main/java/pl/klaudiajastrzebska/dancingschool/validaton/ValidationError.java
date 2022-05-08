package pl.klaudiajastrzebska.dancingschool.validaton;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidationError {
    String message;
    String fieldName;
}
