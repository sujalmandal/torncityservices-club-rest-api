package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;

@Retention(
    RetentionPolicy.RUNTIME
)
@Target(
    ElementType.FIELD
)
public @interface TemplateField{
    public String label() default "";

    public String labelRequest() default "";

    public String labelOffer() default "";

    public boolean optional() default false;

    public ServiceTypeValue serviceType() default ServiceTypeValue.ALL;

    public FieldFormatValue formatter() default FieldFormatValue.TEXT;

    public FieldTypeValue type() default FieldTypeValue.TEXT;

    public String defaultValue() default "";

    public PayFieldTypeValue payFieldType() default PayFieldTypeValue.NONE;

    // only applicable to 'SELECT' type
    public String[] options() default "";

    // only applicable to 'NUMBER' type
    public long minValue() default 0;

    // only applicable to 'NUMBER' type
    public long maxValue() default 10;

    public String maxFieldLabel() default "";

    public String minFieldLabel() default "";

    public boolean isPlayerTarget() default false;

    public boolean isFactionTarget() default false;

    public boolean isSearchable() default true;
}