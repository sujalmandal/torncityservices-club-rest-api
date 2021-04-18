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
    TemplateValue.BOUNTY_PLAYER_REQUEST
)
public class BountyServiceRequest extends ServiceDetail {

    @TemplateField(
	    formLabel = "Torn id of the player on whom bounties have to be placed",
	    optional = true,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    formLabel = "Message while posting the bounty (optional)",
	    optional = true,
	    isSearchable = false
    )
    private String bountyMessage;

    @TemplateField(
	    formLabel = "Total bounties you wish to buy",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max. bounties",
	    filterMinFieldLabel = "min. bounties"
    )
    private Integer totalTimes;

    @TemplateField(
	    formLabel = "Anonymous bounties (optional)",
	    type = FieldTypeValue.CHECKBOX,
	    optional = true,
	    defaultValue = "false"
    )
    private Boolean isAnon = Boolean.FALSE;

    @TemplateField(
	    formLabel = "Maximum price you are willing to pay for each bounty",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 50_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max. pay per bounty",
	    filterMinFieldLabel = "min. pay per bounty"
    )
    private Long amountPerBounty;
}
