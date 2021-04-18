package sujalmandal.torncityservicesclub;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.models.Template;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

public class UtilsTest {

    @Test
    public void testFormDataGeneration() throws JsonProcessingException {
	Set<Template> jsonFormData = TemplateUtil.getTemplatesFromClasses();
	Assert.notEmpty(jsonFormData, "Form data can't be empty!");
    }

}