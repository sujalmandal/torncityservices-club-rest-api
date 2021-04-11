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
    JobDetailTemplateValue.STAT_SPY
)
public class StatSpyJobDetails implements JobDetails {

    @FormField(
	    label = "Torn id of the player to spy on",
	    serviceType = ServiceTypeValue.REQUEST
    )
    private String targetPlayerId;

    @FormField(
	    label = "Total spies to sell",
	    type = FormFieldTypeValue.NUMBER,
	    serviceType = ServiceTypeValue.OFFER,
	    minValue = 1,
	    maxValue = 50
    )
    @FilterableField(
	    label = "total spies",
	    maxFieldLabel = "max no. of spies",
	    minFieldLabel = "min no. of spies"
    )
    private Integer totalSpy;

    @FormField(
	    label = "Pay per spy",
	    formatter = FieldFormatterValue.CURRENCY,
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 750_000,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldType.PER_ACTION
    )
    @FilterableField(
	    label = "total pay per spy",
	    maxFieldLabel = "max pay per spy",
	    minFieldLabel = "min pay per spy"
    )
    private Long pay;
}