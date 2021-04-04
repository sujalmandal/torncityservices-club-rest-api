package sujalmandal.torncityservicesclub;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;
import sujalmandal.torncityservicesclub.utils.TemplateGeneratorUtil;

public class UtilsTest {

    @Test
    public void testFormDataGeneration() throws JsonProcessingException {
	Set<JobDetailFormTemplate> jsonFormData = TemplateGeneratorUtil.generateJobDetailFormTemplates();
	Assert.notEmpty(jsonFormData, "Form data can't be empty!");
    }

}