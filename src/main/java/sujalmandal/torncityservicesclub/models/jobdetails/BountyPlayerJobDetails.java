package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Template(
    TemplateValue.BOUNTY_PLAYER
)
public class BountyPlayerJobDetails implements JobDetails {

    @TemplateField(
	    label = "Torn id of the player on which the bounty has to be placed",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    label = "Message while posting the bounty",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true,
	    isSearchable = false
    )
    private String bountyMessage;

    @TemplateField(
	    label = "Number of bounties to place",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    maxFieldLabel = "maximum bounties",
	    minFieldLabel = "minimum bounties"
    )
    private Integer totalTimes;

    @TemplateField(
	    label = "Anonymous bounties?",
	    serviceType = ServiceTypeValue.REQUEST,
	    type = FieldTypeValue.CHECKBOX,
	    defaultValue = "false"
    )
    private Boolean isAnon = Boolean.FALSE;

    @TemplateField(
	    label = "How much money per bounty",
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 50_000,
	    maxValue = 5_000_000,
	    maxFieldLabel = "maximum pay per bounty",
	    minFieldLabel = "minimum pay per bounty"
    )
    private Long amountPerBounty;
}
