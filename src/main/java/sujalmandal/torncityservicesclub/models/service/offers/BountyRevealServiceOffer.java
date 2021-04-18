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
    TemplateValue.BOUNTY_REVEAL_OFFER
)
public class BountyRevealServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total bounties to sell",
	    formatter = FieldFormatValue.NUMBER,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 25,
	    filterMaxFieldLabel = "max number of bounties to sell",
	    filterMinFieldLabel = "min number of bounties to sell"
    )
    private Integer totalRevealsToSell;

    @TemplateField(
	    formLabel = "Money you going to charge for each bounty reveal",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1_000_000,
	    isMoneyField = true,
	    maxValue = 100_000_000,
	    filterMaxFieldLabel = "max charge per bounty reveal",
	    filterMinFieldLabel = "min charge per bounty reveal pay"
    )
    private Long pay;
}