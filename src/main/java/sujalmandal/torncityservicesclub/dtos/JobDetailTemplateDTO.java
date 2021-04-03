package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;

@Data
public class JobDetailTemplateDTO {
    private String jobDetailFormTemplateName;
    private String jobDetailFormTemplateLabel;
    private String jobDetailFilterTemplateName;
    private String jobDetailFilterTemplateLabel;

    public JobDetailTemplateDTO(JobDetailTemplateValue templateValue) {
	this.jobDetailFormTemplateName = templateValue.getFormTemplateName();
	this.jobDetailFormTemplateLabel = templateValue.getFormTemplateLabel();
	this.jobDetailFilterTemplateName = templateValue.getFilterTemplateName();
	this.jobDetailFilterTemplateLabel = templateValue.getFilterTemplateLabel();
    }
}