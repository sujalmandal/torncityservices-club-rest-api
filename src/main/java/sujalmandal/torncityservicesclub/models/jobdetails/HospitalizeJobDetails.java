package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplate;
import sujalmandal.torncityservicesclub.annotations.ServiceType;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@JobDetailTemplate(JobDetailTemplateValue.HOSPITALIZE)
public class HospitalizeJobDetails implements JobDetails {

    @ServiceType
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player who has to be attacked")
    private String targetPlayerId;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total number of times to hospitalize a target")
    @FilterableField(label = "total hospitalizations", maxFieldLabel = "maximum no. of hospitalizations", minFieldLabel = "minimum no. of hospitalizations", limit = "100")
    private Integer totalHospitalizations;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Pay per hospitalization for this job")
    @FilterableField(label = "total pay per hospitalization", maxFieldLabel = "maximum pay per hospitalization", minFieldLabel = "minimum pay per hospitalization", limit = "5_000_000")
    private Integer pay;
}
