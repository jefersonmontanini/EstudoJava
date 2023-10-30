package MyProject.validation;

import MyProject.validation.constraintValidation.itemValidationNotEmpemty;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)                                     //Indica que vai ocorrer em tempo de execução
@Target(ElementType.FIELD)                                              //Indica que deve ser usado em campos...
@Constraint(validatedBy = itemValidationNotEmpemty.class)                                             //Indica que é uma anotation de validação
public @interface NotEmptyList {

    String message() default "A lista não pode ser vazia";
    Class<?>[] groups() default {};                                     //Propriedade default encontrada em qqr anotation de validação
    Class <? extends Payload> [] payload() default {};                  //Propriedade default encontrada em qqr anotation de validação
}
