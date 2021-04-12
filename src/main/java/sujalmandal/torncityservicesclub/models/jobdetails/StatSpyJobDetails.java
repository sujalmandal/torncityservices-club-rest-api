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
    TemplateValue.STAT_SPY
)
public class StatSpyJobDetails implements JobDetails {

    @TemplateField(
	    label = "Torn id of the player to spy on",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    label = "Total spies to sell",
	    type = FieldTypeValue.NUMBER,
	    serviceType = ServiceTypeValue.OFFER,
	    minValue = 1,
	    maxValue = 50,
	    maxFieldLabel = "max no. of spies",
	    minFieldLabel = "min no. of spies"
    )
    private Integer totalSpy;

    @TemplateField(
	    label = "Pay per spy",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 750_000,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "max pay per spy",
	    minFieldLabel = "min pay per spy"
    )
    private Long pay;
}