package sujalmandal.torncityservicesclub.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.enums.ServiceType;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface HighlightField {
    public ServiceType value() default ServiceType.ALL;
}
