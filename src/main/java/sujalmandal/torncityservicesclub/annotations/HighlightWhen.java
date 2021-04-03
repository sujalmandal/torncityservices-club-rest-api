package sujalmandal.torncityservicesclub.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface HighlightWhen {
    public ServiceTypeValue value() default ServiceTypeValue.ALL;
}
