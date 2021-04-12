package sujalmandal.torncityservicesclub;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.models.FormTemplate;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

public class UtilsTest {

    @Test
    public void testFormDataGeneration() throws JsonProcessingException {
	Set<FormTemplate> jsonFormData = TemplateUtil.generateFormTemplate();
	Assert.notEmpty(jsonFormData, "Form data can't be empty!");
    }

}