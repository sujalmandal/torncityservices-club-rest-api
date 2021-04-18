package sujalmandal.torncityservicesclub.models.service.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Templatized(
    TemplateValue.TRAINS_REQUEST
)
public class TrainServiceRequest extends ServiceDetail {

    @TemplateField(
	    formLabel = "Minimum trains you are willing to buy",
	    type = FieldTypeValue.NUMBER,
	    minValue = 50,
	    maxValue = 500,
	    defaultValue = "50",
	    filterMaxFieldLabel = "max. no. of trains",
	    filterMinFieldLabel = "min. no. of trains"
    )
    private Integer totalTrains;

    @TemplateField(
	    formLabel = "Maximum you are willing to pay per train",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1,
	    maxValue = 750_000,
	    filterMaxFieldLabel = "max. pay per train",
	    filterMinFieldLabel = "min. pay per train"
    )
    private Long amountPerTrain;
}