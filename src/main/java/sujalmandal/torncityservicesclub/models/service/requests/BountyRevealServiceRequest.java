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
    TemplateValue.BOUNTY_REVEAL_REQUEST
)
public class BountyRevealServiceRequest extends ServiceDetail {

    @TemplateField(
	    formLabel = "Player whose bounty has to be revealed",
	    isSearchable = false
    )
    private String playerId;

    @TemplateField(
	    formLabel = "Maximum price you are willing to pay for each bounty reveal",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    filterMaxFieldLabel = "max. pay per bounty",
	    filterMinFieldLabel = "min. pay per bounty"
    )
    private Long pay;
}
