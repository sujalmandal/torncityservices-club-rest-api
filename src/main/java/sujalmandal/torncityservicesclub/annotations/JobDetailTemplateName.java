package sujalmandal.torncityservicesclub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JobDetailTemplateName {
    public JobDetailTemplateValue value();
}