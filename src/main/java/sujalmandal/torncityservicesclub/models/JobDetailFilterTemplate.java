package sujalmandal.torncityservicesclub.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "JobDetailFilterTemplate")
public class JobDetailFilterTemplate {

    @Id
    private String id;
    private String filterTemplateName;
    private String filterTemplateLabel;
    List<FilterFieldDescriptor> filterElements = new ArrayList<>();

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
	JobDetailFilterTemplate other = (JobDetailFilterTemplate) obj;
	if (filterTemplateName == null) {
	    if (other.filterTemplateName != null)
		return false;
	} else if (!filterTemplateName.equals(other.filterTemplateName))
	    return false;
	return true;
    }

}