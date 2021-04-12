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
    TemplateValue.ATTACK
)
public class FightJobDetails implements JobDetails {

    @TemplateField(
	    label = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    label = "Total attacks",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 200,
	    maxFieldLabel = "maximum no. of attacks",
	    minFieldLabel = "minimum no. of attacks"
    )
    private Integer totalAttacks;

    @TemplateField(
	    label = "Pay for per attack this job",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 100_000,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "maximum pay per attack",
	    minFieldLabel = "minimum pay per attack"
    )
    private Long pay;
}
