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
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@GenerateTemplate(
    JobDetailTemplateValue.RENT
)
public class RentJobDetails implements JobDetails {

    @FormField(
	    label = "Total happy",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 5000
    )
    @FilterableField(
	    label = "total happy for the property",
	    maxFieldLabel = "max happy",
	    minFieldLabel = "min happy"
    )
    private String happy;

    @FormField(
	    label = "Total number of days for rent",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 7,
	    maxValue = 100
    )
    @FilterableField(
	    label = "total rent duration in days",
	    maxFieldLabel = "max rented days",
	    minFieldLabel = "min rented days"
    )
    private Integer durationInDays;

    @FormField(
	    label = "Total rent",
	    type = FormFieldTypeValue.NUMBER,
	    formatter = FieldFormatterValue.CURRENCY,
	    minValue = 1,
	    maxValue = 100_000_000,
	    payFieldType = PayFieldType.TOTAL
    )
    @FilterableField(
	    label = "total rent",
	    maxFieldLabel = "max rent",
	    minFieldLabel = "min rent"
    )
    private Long rent;

}