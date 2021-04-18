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
    TemplateValue.BOUNTY_PLAYER_OFFER
)
public class BountyServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Number of bounties to sell",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max bounties to sell",
	    filterMinFieldLabel = "min bounties to sell"
    )
    private Integer totalBountiesToSell;

    @TemplateField(
	    formLabel = "Money to charge for each bounty",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 50_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max charge per bounty",
	    filterMinFieldLabel = "min charge per bounty"
    )
    private Long costPerBounty;
}
