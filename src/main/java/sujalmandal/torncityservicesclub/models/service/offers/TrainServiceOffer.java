package sujalmandal.torncityservicesclub.models.service.offers;

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
    TemplateValue.TRAINS_OFFER
)
public class TrainServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total trains you wish to sell",
	    type = FieldTypeValue.NUMBER,
	    minValue = 50,
	    maxValue = 500,
	    defaultValue = "50",
	    filterMaxFieldLabel = "max. no. of trains",
	    filterMinFieldLabel = "min. no. of trains"
    )
    private Integer totalTrains;

    @TemplateField(
	    formLabel = "Minimum price you expect per train",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1,
	    maxValue = 750_000,
	    filterMaxFieldLabel = "max. pay per train",
	    filterMinFieldLabel = "min. pay per train"
    )
    private Long amountPerTrain;
}