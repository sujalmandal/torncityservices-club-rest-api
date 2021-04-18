package sujalmandal.torncityservicesclub.models;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.dtos.commons.TemplateMetaInfo;
import sujalmandal.torncityservicesclub.utils.PojoUtils;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

@SuppressWarnings(
    "unchecked"
)
public abstract class ServiceDetail {

    public static ServiceDetail fromMap(String jobDetailFormTemplateName, HashMap<String, Object> data) {
	Optional<Class<?>> clazz = getServiceDetailTemplate(jobDetailFormTemplateName);
	if (clazz.isPresent()) {
	    return (ServiceDetail) PojoUtils.getObjectMapper().convertValue(data, clazz.get());
	} else {
	    return null;
	}
    }

    public HashMap<String, Object> toMap() {
	return PojoUtils.getObjectMapper().convertValue(this, HashMap.class);
    }

    private static Optional<Class<?>> getServiceDetailTemplate(String templateName) {
	Optional<Class<?>> clazz = TemplateUtil.getTemplatizedClasses().stream().filter(implClass -> {
	    return implClass.getAnnotation(Templatized.class).value().getTemplateName().equals(templateName);
	}).findFirst();
	return clazz;
    }

    public static Map<String, TemplateField> getFieldDetails(String templateName) {
	Map<String, TemplateField> formFields = new HashMap<String, TemplateField>();
	for (Field f : getServiceDetailTemplate(templateName).get().getDeclaredFields()) {
	    formFields.put(f.getName(), f.getAnnotation(TemplateField.class));
	}
	return formFields;
    }

    public TemplateMetaInfo getTemplateMetadata() {
	TemplateMetaInfo templateMetaInfo = new TemplateMetaInfo();
	TemplateValue template = this.getClass().getAnnotation(Templatized.class).value();
	PojoUtils.map(template, templateMetaInfo);
	return templateMetaInfo;
    }

}