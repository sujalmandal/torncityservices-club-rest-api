package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Template(
    TemplateValue.DEFEND_FACTION
)
public class DefendFactionJobDetails implements JobDetails {

    @TemplateField(
	    label = "Faction to defend",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String factionName;

    @TemplateField(
	    label = "Total duration for which attackers on this factions have to be hit",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    maxFieldLabel = "maximum days protection",
	    minFieldLabel = "minimum days protection"
    )
    private String durationDays;

    @TemplateField(
	    label = "Total pay for this job",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 10_000_000,
	    maxValue = 500_000_000,
	    payFieldType = PayFieldTypeValue.TOTAL,
	    maxFieldLabel = "maximum pay",
	    minFieldLabel = "minimum pay"
    )
    private Long pay;
}
