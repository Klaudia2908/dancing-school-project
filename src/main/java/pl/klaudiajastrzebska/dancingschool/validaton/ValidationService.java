package pl.klaudiajastrzebska.dancingschool.validaton;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ValidationService {
    private final ValidatorFactory validatorFactory;

    public void validate(Object objectToValidate) {
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(objectToValidate);

        if(!constraintViolations.isEmpty()){
            throw new ValidationException(buildValidationErrors(constraintViolations), objectToValidate);
        }
    }

    private List<ValidationError> buildValidationErrors(Set<ConstraintViolation<Object>> violations){
        return violations
                .stream()
                .map(violation -> ValidationError
                        .builder()
                        .fieldName(violation.getPropertyPath().toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
