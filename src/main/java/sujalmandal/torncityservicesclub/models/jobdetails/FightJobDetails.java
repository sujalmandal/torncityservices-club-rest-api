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
    JobDetailTemplateValue.ATTACK
)
public class FightJobDetails implements JobDetails {

    @FormField(
	    label = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST
    )
    private String targetPlayerId;

    @FormField(
	    label = "Total attacks",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 200
    )
    @FilterableField(
	    label = "total attacks",
	    maxFieldLabel = "maximum no. of attacks",
	    minFieldLabel = "minimum no. of attacks"
    )
    private Integer totalAttacks;

    @FormField(
	    label = "Pay for per attack this job",
	    formatter = FieldFormatterValue.CURRENCY,
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 100_000,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldType.PER_ACTION
    )
    @FilterableField(
	    label = "total pay per attack",
	    maxFieldLabel = "maximum pay per attack",
	    minFieldLabel = "minimum pay per attack"
    )
    private Long pay;
}
