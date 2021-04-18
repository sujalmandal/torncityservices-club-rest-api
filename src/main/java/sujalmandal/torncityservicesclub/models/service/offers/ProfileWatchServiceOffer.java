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
    TemplateValue.PROFILE_WATCH_OFFER
)
public class ProfileWatchServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total duration, in hours, you are willing to watch a profile",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 24,
	    filterMaxFieldLabel = "max no. of watch hours",
	    filterMinFieldLabel = "min no. of watch hours"
    )
    private Integer durationHours;

    @TemplateField(
	    formLabel = "Minimum total pay you require for this service",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    filterMaxFieldLabel = "max pay",
	    filterMinFieldLabel = "min pay"
    )
    private Long pay;
}
