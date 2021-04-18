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
    TemplateValue.STAT_SPY_OFFER
)
public class StatSpyServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total spies to sell",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max no. of spies",
	    filterMinFieldLabel = "min no. of spies"
    )
    private Integer totalSpy;

    @TemplateField(
	    formLabel = "Minium pay you require for each spy",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 750_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max. cost per spy",
	    filterMinFieldLabel = "min. cost per spy"
    )
    private Long pay;
}