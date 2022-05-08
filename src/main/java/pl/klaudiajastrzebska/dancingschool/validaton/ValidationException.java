package pl.klaudiajastrzebska.dancingschool.validaton;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
public class ValidationException extends RuntimeException {
    List<ValidationError> validationErrors;
    Object validatedCommand;
}
