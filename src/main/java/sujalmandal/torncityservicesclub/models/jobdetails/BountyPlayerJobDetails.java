package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.annotations.ServiceType;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.BOUNTY_PLAYER)
public class BountyPlayerJobDetails implements JobDetails {

    @ServiceType
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player on which the bounty has to be placed")
    private String targetPlayerId;

    @ServiceType
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Message while posting the bounty")
    private String bountyMessage;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Number of bounties to place")
    @FilterableField(label = "total bounties", maxFieldLabel = "maximum bounties", minFieldLabel = "minimum bounties", limit = "100")
    private Integer totalTimes;

    @ServiceType
    @JobDetailFieldType(JobDetailFieldTypeValue.CHECKBOX)
    @JobDetailFieldLabel("Anonymous bounties?")
    @FilterableField(label = "Anonymous or not")
    private Boolean isAnon = Boolean.FALSE;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("How much money per bounty")
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay per bounty", minFieldLabel = "minimum pay per bounty", limit = "100_000_000")
    private Integer amountPerBounty;
}
