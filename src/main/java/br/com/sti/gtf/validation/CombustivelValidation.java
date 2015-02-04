package br.com.sti.gtf.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.sti.gtf.validation.impl.CombustivelValidator;

/**
 *
 * @author Phelipe Melanias
 */
@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=CombustivelValidator.class)
public @interface CombustivelValidation {

    String message() default "{br.com.sti.gtf.validation.CombustivelValidation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}