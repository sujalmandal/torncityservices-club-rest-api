package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.DIRTY_BOMB_A_FACTION)
public class DirtyBombJobDetails implements JobDetails {
	
	@FormField(label = "Faction to attack", serviceType = ServiceTypeValue.REQUEST)
	private String factionName;
	
	@HighlightWhen
	@FormField(label = "Pay for this job (better be good)", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, minValue = "100_000_000", maxValue = "5_000_000_000")
	@FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay")
	private Long pay;
	
}