package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JobDetailFieldType {
    public JobDetailFieldTypeValue value() default JobDetailFieldTypeValue.TEXT;
}