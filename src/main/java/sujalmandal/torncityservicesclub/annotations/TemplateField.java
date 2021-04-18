package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;

@Retention(
    RetentionPolicy.RUNTIME
)
@Target(
    ElementType.FIELD
)
public @interface TemplateField{

    /** labels **/
    public String formLabel() default "";

    public String viewLabel() default "";

    public String filterCommonLabel() default "";

    public String filterMaxFieldLabel() default "";

    public String filterMinFieldLabel() default "";

    public boolean optional() default false;

    public FieldFormatValue formatter() default FieldFormatValue.TEXT;

    public FieldTypeValue type() default FieldTypeValue.TEXT;

    public String defaultValue() default "";

    public boolean isMoneyField() default false;

    public boolean isPlayerTarget() default false;

    public boolean isFactionTarget() default false;

    // only applicable to 'SELECT' type
    public String[] options() default "";

    /* filter related options */

    // only applicable to 'NUMBER' type
    public long minValue() default 0;

    // only applicable to 'NUMBER' type
    public long maxValue() default 10;

    public boolean isSearchable() default true;
}