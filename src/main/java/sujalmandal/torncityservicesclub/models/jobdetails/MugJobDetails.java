package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.MUG)
public class MugJobDetails implements JobDetails {
	
	@FormField(label = "Torn id of the player who has to be attacked", serviceType = ServiceTypeValue.REQUEST)
	private String targetPlayerId;
	
	@FormField(label = "Total mugs", type = FormFieldTypeValue.NUMBER, minValue = "1", maxValue = "50")
	@FilterableField(label = "total mugs", maxFieldLabel = "maximum no. of mugs", minFieldLabel = "minimum no. of mugs")
	private Integer totalMugs;
	
	@FormField(label = "Pay per mug", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, minValue = "0", maxValue = "5_000_000")
	@FilterableField(label = "pay per mug", maxFieldLabel = "maximum pay per mug", minFieldLabel = "minimum pay per mug")
	private Integer pay;
	
}
