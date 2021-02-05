package br.com.zup.mercadolivre.config.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NotRepeatedValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRepeated {

    String message() default "Os dados não podem ser repetidos.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
