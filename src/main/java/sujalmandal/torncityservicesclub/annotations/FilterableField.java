package sujalmandal.torncityservicesclub.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface FilterableField {
    public String label();

    public String maxFieldLabel() default "";;

    public String minFieldLabel() default "";;

}
