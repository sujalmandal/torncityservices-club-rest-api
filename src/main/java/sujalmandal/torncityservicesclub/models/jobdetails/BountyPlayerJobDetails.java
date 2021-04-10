package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.BOUNTY_PLAYER)
public class BountyPlayerJobDetails implements JobDetails {

    @FormField(label = "Torn id of the player on which the bounty has to be placed", serviceType = ServiceTypeValue.REQUEST, optional = true)
    private String targetPlayerId;

    @FormField(label = "Message while posting the bounty", serviceType = ServiceTypeValue.REQUEST, optional = true)
    private String bountyMessage;

    @FormField(label = "Number of bounties to place", type = FormFieldTypeValue.NUMBER, minValue = 1, maxValue = 50)
    @FilterableField(label = "total bounties", maxFieldLabel = "maximum bounties", minFieldLabel = "minimum bounties")
    private Integer totalTimes;

    @FormField(label = "Anonymous bounties?", serviceType = ServiceTypeValue.REQUEST, type = FormFieldTypeValue.CHECKBOX, defaultValue = "false")
    @FilterableField(label = "Anonymous or not")
    private Boolean isAnon = Boolean.FALSE;

    @HighlightWhen
    @FormField(label = "How much money per bounty", formatter = FieldFormatterValue.CURRENCY, type = FormFieldTypeValue.NUMBER, minValue = 50_000, maxValue = 5_000_000)
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay per bounty", minFieldLabel = "minimum pay per bounty")
    private Long amountPerBounty;
}
