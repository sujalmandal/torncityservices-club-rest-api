package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.PayFieldType;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@GenerateTemplate(
    JobDetailTemplateValue.REVIVE_FACTION
)
public class FactionReviveJobDetails implements JobDetails {

    @FormField(
	    label = "Faction to revive",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true
    )
    private String factionName;

    @FormField(
	    label = "Total revives",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true
    )
    @FilterableField(
	    label = "Total revives",
	    maxFieldLabel = "max revives",
	    minFieldLabel = "min revives"
    )
    private Integer totalRevives;

    @FormField(
	    label = "Duration over which revives need to be done",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true
    )
    @FilterableField(
	    label = "duration over which revives need to be done",
	    maxFieldLabel = "max days",
	    minFieldLabel = "min days"
    )
    private Integer durationDays;

    @FormField(
	    label = "Pay per revive",
	    formatter = FieldFormatterValue.CURRENCY,
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 250_000,
	    maxValue = 3_000_000,
	    payFieldType = PayFieldType.PER_ACTION
    )
    @FilterableField(
	    label = "Pay per revive",
	    maxFieldLabel = "max pay",
	    minFieldLabel = "min pay"
    )
    private Long pay;
}
