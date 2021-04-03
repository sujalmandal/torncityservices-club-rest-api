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
@JobDetailTemplateKey(JobDetailTemplateValue.MUG)
public class MugJobDetails implements JobDetails {

    @RequestServiceAttribute
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player who has to be attacked")
    private String targetPlayerId;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total attacks")
    private Integer totalMugs;

    @FieldFormatter(FieldFormat.CURRENCY)
    @HighlightField
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total pay for this job")
    private Integer pay;

}
