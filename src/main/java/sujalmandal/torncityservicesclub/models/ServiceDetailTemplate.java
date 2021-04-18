package sujalmandal.torncityservicesclub.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Data;
import sujalmandal.torncityservicesclub.dtos.commons.TemplateMetaInfo;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Data
@Document(
	collection = "ServiceDetailTemplate"
)
public class ServiceDetailTemplate implements Template {

    @Id
    private String id;

    private TemplateMetaInfo templateInfo;

    private List<FormFieldDescriptor> formElements = new ArrayList<>();
    private List<FilterFieldDescriptor> filterElements = new ArrayList<>();
    private List<ViewFieldDescriptor> viewElements = new ArrayList<>();

    public String toJson() throws JsonProcessingException {
	return PojoUtils.getObjectMapper().writeValueAsString(this);
    }
}