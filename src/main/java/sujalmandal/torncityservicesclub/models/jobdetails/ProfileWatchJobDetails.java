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
@GenerateTemplate(JobDetailTemplateValue.PROFILE_WATCH)
public class ProfileWatchJobDetails implements JobDetails {

    @FormField(label = "Torn id of the player who has to be attacked", serviceType = ServiceTypeValue.REQUEST)
    private String targetPlayerId;

    @FormField(label = "Minimum watch hours", type = FormFieldTypeValue.NUMBER, minValue = "1", maxValue = "5")
    @FilterableField(label = "total duration in hours", maxFieldLabel = "maximum no. of hours", minFieldLabel = "minimum no. of hours")
    private Integer durationHours;

    @FormField(label = "Total pay for this job", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, maxValue = "100_000_000")
    @FilterableField(label = "total pay for this job", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay")
    private Integer pay;
}
