package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
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
    TemplateValue.MUG
)
public class MugJobDetails implements JobDetails {

    @TemplateField(
	    labelCommon = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    labelCommon = "Total mugs",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    maxFieldLabel = "max no. of mugs",
	    minFieldLabel = "min no. of mugs"
    )
    private Integer totalMugs;

    @TemplateField(
	    labelCommon = "Pay per mug",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 0,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    payOnServiceType = ServiceTypeValue.REQUEST,
	    maxFieldLabel = "max pay per mug",
	    minFieldLabel = "min pay per mug"
    )
    private Long pay;

}
