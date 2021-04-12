package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Template(
    TemplateValue.TRAINS
)
public class TrainJobDetails implements JobDetails {

    @TemplateField(
	    label = "How many trains in total",
	    type = FieldTypeValue.NUMBER,
	    minValue = 50,
	    maxValue = 500,
	    defaultValue = "50",
	    maxFieldLabel = "max. no. of trains",
	    minFieldLabel = "min. no. of trains"
    )
    private Integer totalTrains;

    @TemplateField(
	    label = "Money per train",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1,
	    maxValue = 750_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "max. pay per train",
	    minFieldLabel = "min. pay per train"
    )
    private Long amountPerTrain;
}