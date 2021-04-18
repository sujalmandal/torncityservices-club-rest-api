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
    TemplateValue.REVIVE_CONTRACT_OFFER
)
public class ReviveContractServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total revives you want to sell",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true,
	    filterMaxFieldLabel = "max. revives",
	    filterMinFieldLabel = "min. revives"
    )
    private Integer totalRevives;

    @TemplateField(
	    formLabel = "Minumum pay you expect per revive",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 250_000,
	    maxValue = 3_000_000,
	    filterMaxFieldLabel = "max. pay per revive",
	    filterMinFieldLabel = "min. pay per revive"
    )
    private Long pay;
}