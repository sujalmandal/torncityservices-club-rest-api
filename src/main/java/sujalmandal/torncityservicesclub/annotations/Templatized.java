package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import sujalmandal.torncityservicesclub.constants.TemplateValue;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Templatized {
    public TemplateValue value();
}