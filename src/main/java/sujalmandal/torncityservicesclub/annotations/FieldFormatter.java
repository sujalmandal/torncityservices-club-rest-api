package sujalmandal.torncityservicesclub.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.enums.FieldFormat;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldFormatter {
    public FieldFormat value() default FieldFormat.TEXT;
}
