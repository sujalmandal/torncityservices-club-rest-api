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
    TemplateValue.ATTACK_FACTION
)
public class AttackFactionJobDetails implements JobDetails {

    @TemplateField(
	    label = "Faction to attack",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true,
	    isSearchable = false
    )
    private String factionName;

    @TemplateField(
	    label = "Total attacks on faction members",
	    formatter = FieldFormatValue.NUMBER,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 500,
	    optional = true,
	    maxFieldLabel = "maximum no. of attacks",
	    minFieldLabel = "minimum no. of attacks"
    )
    private String totalAttacks;

    @TemplateField(
	    label = "Total duration in days over which the attack has to take place",
	    formatter = FieldFormatValue.NUMBER,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 7,
	    maxFieldLabel = "maximum no. of days",
	    minFieldLabel = "minimum no. of days"
    )
    private String duration;

    @TemplateField(
	    label = "Total pay for this job",
	    payFieldType = PayFieldTypeValue.TOTAL,
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 10_000_000,
	    maxValue = 500_000_000,
	    maxFieldLabel = "maximum pay",
	    minFieldLabel = "minimum pay"
    )
    private Long pay;
}