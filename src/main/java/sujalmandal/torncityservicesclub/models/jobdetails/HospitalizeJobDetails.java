package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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
@ToString
@GenerateTemplate(JobDetailTemplateValue.HOSPITALIZE)
public class HospitalizeJobDetails implements JobDetails {

    @FormField(label = "Torn id of the player who has to be attacked", serviceType = ServiceTypeValue.REQUEST)
    private String targetPlayerId;

    @FormField(label = "Total number of times to hospitalize a target", type = FormFieldTypeValue.NUMBER, minValue = "1", maxValue = "50")
    @FilterableField(label = "total hospitalizations", maxFieldLabel = "maximum no. of hospitalizations", minFieldLabel = "minimum no. of hospitalizations")
    private Integer totalHospitalizations;

    @HighlightWhen
    @FormField(label = "Pay per hospitalization for this job", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, maxValue = "5_000_000")
    @FilterableField(label = "total pay per hospitalization", maxFieldLabel = "maximum pay per hospitalization", minFieldLabel = "minimum pay per hospitalization")
    private Integer pay;
}
