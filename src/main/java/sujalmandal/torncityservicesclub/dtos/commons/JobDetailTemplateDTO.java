package sujalmandal.torncityservicesclub.dtos.commons;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.TemplateValue;

@Data
public class JobDetailTemplateDTO {
    private String jobDetailFormTemplateName;
    private String jobDetailFormTemplateLabel;
    private String jobDetailFilterTemplateName;
    private String jobDetailFilterTemplateLabel;

    public JobDetailTemplateDTO(TemplateValue templateValue) {
	this.jobDetailFormTemplateName = templateValue.getFormTemplateName();
	this.jobDetailFormTemplateLabel = templateValue.getFormTemplateLabel();
	this.jobDetailFilterTemplateName = templateValue.getFilterTemplateName();
	this.jobDetailFilterTemplateLabel = templateValue.getFilterTemplateLabel();
    }
}