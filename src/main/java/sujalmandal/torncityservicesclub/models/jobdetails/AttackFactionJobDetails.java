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
@GenerateTemplate(JobDetailTemplateValue.ATTACK_FACTION)
public class AttackFactionJobDetails implements JobDetails {

    @ServiceType
    @JobDetailFieldLabel("Faction to attack")
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    private String factionName;

    @FieldFormatter(FieldFormatterValue.NUMBER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total attacks on faction members")
    @FilterableField(label = "total attacks", maxFieldLabel = "maximum no. of attacks", minFieldLabel = "minimum no. of attacks", limit = "200")
    private String totalAttacks;

    @FieldFormatter(FieldFormatterValue.NUMBER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total duration in days over which the attack has to take place")
    @FilterableField(label = "total duration in days", maxFieldLabel = "maximum no. of days", minFieldLabel = "minimum no. of days", limit = "10")
    private String duration;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total pay for this job")
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay", limit = "500_000_000")
    private Integer pay;
}