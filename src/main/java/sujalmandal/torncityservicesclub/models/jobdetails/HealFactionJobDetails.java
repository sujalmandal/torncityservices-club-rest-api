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
@GenerateTemplate(JobDetailTemplateValue.REVIVE_FACTION)
public class HealFactionJobDetails implements JobDetails {

    @FormField(label = "Faction to revive", serviceType = ServiceTypeValue.REQUEST)
    private String factionName;

    @FormField(label = "Total duration for which revives have to be done", type = FormFieldTypeValue.NUMBER, minValue = 1, maxValue = 5)
    @FilterableField(label = "total duration in days", maxFieldLabel = "maximum days protection", minFieldLabel = "minimum days protection")
    private String durationDays;

    @HighlightWhen
    @FormField(label = "Total pay for this job", formatter = FieldFormatterValue.CURRENCY, type = FormFieldTypeValue.NUMBER, minValue = 10_000_000, maxValue = 1000_000_000)
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay")
    private Long pay;
}
