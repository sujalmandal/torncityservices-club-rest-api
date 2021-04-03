package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.HighlightField;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplateKey;
import sujalmandal.torncityservicesclub.annotations.RequestServiceAttribute;
import sujalmandal.torncityservicesclub.enums.FieldFormat;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@JobDetailTemplateKey(JobDetailTemplateValue.BOUNTY_PLAYER)
public class BountyPlayerJobDetails implements JobDetails {

    @RequestServiceAttribute
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player on which the bounty has to be placed")
    private String targetPlayerId;

    @RequestServiceAttribute
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Message while posting the bounty")
    private String bountyMessage;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Number of bounties to place")
    private Integer totalTimes;

    @RequestServiceAttribute
    @JobDetailFieldType(JobDetailFieldTypeValue.CHECKBOX)
    @JobDetailFieldLabel("Anonymous bounties?")
    private Boolean isAnon = Boolean.FALSE;

    @FieldFormatter(FieldFormat.CURRENCY)
    @HighlightField
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("How much money per bounty")
    private Integer amountPerBounty;
}
