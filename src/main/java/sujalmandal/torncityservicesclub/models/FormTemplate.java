package sujalmandal.torncityservicesclub.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.Setter;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Getter
@Setter
@Document(
	collection = "FormTemplate"
)
public class FormTemplate {
    @Id
    private String id;
    private String formTemplateName;
    private String formTemplateLabel;
    private String filterTemplateName;
    private String filterTemplateLabel;
    private String formRequestTypeLabel;
    private String formOfferTypeLabel;

    private List<FormFieldDescriptor> elements = new ArrayList<FormFieldDescriptor>();

    public String toJson() throws JsonProcessingException {
	FormTemplate copyOfThis = new FormTemplate();
	PojoUtils.map(this, copyOfThis);
	copyOfThis.id = null;
	return PojoUtils.getObjectMapper().writeValueAsString(copyOfThis);
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	FormTemplate other = (FormTemplate) obj;
	if (formTemplateName == null) {
	    if (other.formTemplateName != null)
		return false;
	} else if (!formTemplateName.equals(other.formTemplateName))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(formTemplateName);
    }

}