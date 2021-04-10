package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.ATTACK_FACTION)
public class AttackFactionJobDetails implements JobDetails {

    @FormField(label = "Faction to attack", serviceType = ServiceTypeValue.REQUEST, optional = true)
    private String factionName;

    @FormField(label = "Total attacks on faction members", formatter = FieldFormatterValue.NUMBER, type = FormFieldTypeValue.NUMBER, minValue = 1, maxValue = 500, optional = true)
    @FilterableField(label = "total attacks", maxFieldLabel = "maximum no. of attacks", minFieldLabel = "minimum no. of attacks")
    private String totalAttacks;

    @FormField(label = "Total duration in days over which the attack has to take place", formatter = FieldFormatterValue.NUMBER, type = FormFieldTypeValue.NUMBER, minValue = 1, maxValue = 7)
    @FilterableField(label = "total duration in days", maxFieldLabel = "maximum no. of days", minFieldLabel = "minimum no. of days")
    private String duration;

    @FormField(label = "Total pay for this job", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, minValue = 10_000_000, maxValue = 500_000_000)
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay")
    private Long pay;
}