package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@JobDetailTemplate(JobDetailTemplateValue.DIRTY_BOMB_A_FACTION)
public class DirtyBombJobDetails implements JobDetails {

    @JobDetailFieldLabel("Faction to attack")
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    private String factionName;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldLabel("Pay for this job (better be good)")
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay", limit = "1_000_000_000")
    private Integer pay;

}