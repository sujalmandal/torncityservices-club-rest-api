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
@GenerateTemplate(JobDetailTemplateValue.MUG)
public class MugJobDetails implements JobDetails {

    @ServiceType(ServiceTypeValue.REQUEST)
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player who has to be attacked")
    private String targetPlayerId;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total mugs")
    @FilterableField(label = "total mugs", maxFieldLabel = "maximum no. of mugs", minFieldLabel = "minimum no. of mugs", limit = "100")
    private Integer totalMugs;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Pay per mug")
    @FilterableField(label = "pay per mug", maxFieldLabel = "maximum pay per mug", minFieldLabel = "minimum pay per mug", limit = "5_000_000")
    private Integer pay;

}
