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
    JobDetailTemplateValue.DEFEND_FACTION
)
public class DefendFactionJobDetails implements JobDetails {

    @FormField(
	    label = "Faction to defend",
	    serviceType = ServiceTypeValue.REQUEST
    )
    private String factionName;

    @FormField(
	    label = "Total duration for which attackers on this factions have to be hit",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5
    )
    @FilterableField(
	    label = "total duration in days",
	    maxFieldLabel = "maximum days protection",
	    minFieldLabel = "minimum days protection"
    )
    private String durationDays;

    @FormField(
	    label = "Total pay for this job",
	    formatter = FieldFormatterValue.CURRENCY,
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 10_000_000,
	    maxValue = 500_000_000,
	    payFieldType = PayFieldType.TOTAL
    )
    @FilterableField(
	    label = "total pay",
	    maxFieldLabel = "maximum pay",
	    minFieldLabel = "minimum pay"
    )
    private Long pay;
}
