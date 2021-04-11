package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.PayFieldType;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@GenerateTemplate(
    JobDetailTemplateValue.PROFILE_WATCH
)
public class ProfileWatchJobDetails implements JobDetails {

    @FormField(
	    label = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true
    )
    private String targetPlayerId;

    @FormField(
	    label = "Total watch hours",
	    type = FormFieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 24
    )
    @FilterableField(
	    label = "total watch duration in hours",
	    maxFieldLabel = "max no. of hours",
	    minFieldLabel = "min no. of hours"
    )
    private Integer durationHours;

    @FormField(
	    label = "Total pay for this job",
	    type = FormFieldTypeValue.NUMBER,
	    formatter = FieldFormatterValue.CURRENCY,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    payFieldType = PayFieldType.TOTAL
    )
    @FilterableField(
	    label = "total pay for this job",
	    maxFieldLabel = "max pay",
	    minFieldLabel = "min pay"
    )
    private Long pay;
}
