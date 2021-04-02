package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;

@Data
public class JobDetailKeyDTO {
    private String key;
    private String label;

    public JobDetailKeyDTO(JobDetailTemplateValue templateValue) {
	this.key = templateValue.getKey();
	this.label = templateValue.getLabel();
    }
}