package sujalmandal.torncityservicesclub.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.Getter;
import lombok.Setter;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Getter
@Setter
@Document(collection = "JobDetailTemplate")
public class JobDetailTemplate {
    @Id
    private String id;
    private String key;
    private String label;
    private List<FormFieldDescriptor> elements = new ArrayList<FormFieldDescriptor>();

    public String toJson() throws JsonProcessingException {
	JobDetailTemplate copyOfThis = new JobDetailTemplate();
	PojoUtils.getModelMapper().map(this, copyOfThis);
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
	JobDetailTemplate other = (JobDetailTemplate) obj;
	if (key == null) {
	    if (other.key != null)
		return false;
	} else if (!key.equals(other.key))
	    return false;
	return true;
    }

    @Override
    public int hashCode() {
	return Objects.hash(key);
    }

}