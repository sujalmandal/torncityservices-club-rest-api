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
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.DEFEND_FACTION)
public class DefendFactionJobDetails implements JobDetails {

    @ServiceType(ServiceTypeValue.REQUEST)
    @JobDetailFieldLabel("Faction to defend")
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    private String factionName;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total duration for which attackers on this factions have to be hit")
    @FilterableField(label = "total duration in days", maxFieldLabel = "maximum days protection", minFieldLabel = "minimum days protection", limit = "10")
    private String durationDays;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total pay for this job")
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay", limit = "500_000_000")
    private Integer pay;
}
