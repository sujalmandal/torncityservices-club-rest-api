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
    TemplateValue.RENT
)
public class RentJobDetails implements JobDetails {

    @TemplateField(
	    labelCommon = "Total happy",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 5000,
	    maxFieldLabel = "max happy",
	    minFieldLabel = "min happy"
    )
    private String happy;

    @TemplateField(
	    labelCommon = "Total number of days for rent",
	    type = FieldTypeValue.NUMBER,
	    minValue = 7,
	    maxValue = 100,
	    maxFieldLabel = "max rented days",
	    minFieldLabel = "min rented days"
    )
    private Integer durationInDays;

    @TemplateField(
	    labelCommon = "Total rent",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1,
	    payOnServiceType = ServiceTypeValue.OFFER,
	    maxValue = 100_000_000,
	    payFieldType = PayFieldTypeValue.TOTAL,
	    maxFieldLabel = "max rent",
	    minFieldLabel = "min rent"
    )
    private Long rent;

}