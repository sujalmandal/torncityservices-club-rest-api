package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormField {
    public String label() default "";

    public boolean optional() default true;

    public ServiceTypeValue serviceType() default ServiceTypeValue.ALL;

    public FieldFormatterValue formatter() default FieldFormatterValue.TEXT;

    public FormFieldTypeValue type() default FormFieldTypeValue.TEXT;

    public String defaultValue() default "";

    // only applicable to 'SELECT' type
    public String[] options() default "";

    // only applicable to 'NUMBER' type
    public long minValue() default 0;

    // only applicable to 'NUMBER' type
    public long maxValue() default 10;
}