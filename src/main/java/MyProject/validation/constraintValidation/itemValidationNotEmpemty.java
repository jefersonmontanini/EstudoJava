package MyProject.validation.constraintValidation;

import MyProject.validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class itemValidationNotEmpemty implements ConstraintValidator<NotEmptyList, List> {                  //PARAMETRO1: Annotation, PARAMETRO2: tipo de dado que vai ser validado

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {              //VAI DIZER SE O OBJETO É VALIDO
        return list != null && !list.isEmpty();                                                             //lISTA NÃO É NULA NEM VAZIA
    }
}
