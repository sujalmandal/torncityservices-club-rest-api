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
    TemplateValue.BOUNTY_REVEAL
)
public class BountyRevealJobDetails implements JobDetails {

    @TemplateField(
	    label = "Player whose bounty has to be revealed",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String playerId;

    @TemplateField(
	    label = "Cost of a bounty reveal",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "maximum pay",
	    minFieldLabel = "minimum pay"
    )
    private Long pay;
}
