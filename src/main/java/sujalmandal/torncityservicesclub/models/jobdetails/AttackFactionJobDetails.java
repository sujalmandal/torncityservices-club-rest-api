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
@JobDetailTemplateKey(JobDetailTemplateValue.ATTACK_FACTION)
public class AttackFactionJobDetails implements JobDetails {

    @RequestServiceAttribute
    @JobDetailFieldLabel("Faction to attack")
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    private String factionName;

    @FieldFormatter(FieldFormat.NUMBER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total attacks on faction members")
    private String totalAttacks;

    @FieldFormatter(FieldFormat.NUMBER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total duration in days over which the attack has to take place")
    private String duration;

    @FieldFormatter(FieldFormat.CURRENCY)
    @HighlightField
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total pay for this job")
    private Integer pay;
}