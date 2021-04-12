package sujalmandal.torncityservicesclub.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.Setter;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Getter
@Setter
@Document(
	collection = "FilterTemplate"
)
public class FilterTemplate {

    @Id
    private String id;
    private String filterTemplateName;
    private String filterTemplateLabel;
    List<FilterFieldDescriptor> filterElements = new ArrayList<>();
    private String filterRequestTypeLabel;
    private String filterOfferTypeLabel;

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((filterTemplateName == null) ? 0 : filterTemplateName.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	FilterTemplate other = (FilterTemplate) obj;
	if (filterTemplateName == null) {
	    if (other.filterTemplateName != null)
		return false;
	} else if (!filterTemplateName.equals(other.filterTemplateName))
	    return false;
	return true;
    }

    public Object toJson() throws JsonProcessingException {
	FilterTemplate copyOfThis = new FilterTemplate();
	PojoUtils.map(this, copyOfThis);
	copyOfThis.id = null;
	return PojoUtils.getObjectMapper().writeValueAsString(copyOfThis);
    }

}