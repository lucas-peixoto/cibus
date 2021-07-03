package br.com.cibus.geral.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CnpjValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Cnpj {
    String message() default "Cnpj inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
