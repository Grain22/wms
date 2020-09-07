package grain.configs.validators;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CheckOptionValueValidator implements ConstraintValidator<CheckOptionValue, String> {

    private String[] value;

    @Override
    public void initialize(CheckOptionValue constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String s : this.value) {
            if (value.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
