package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Template(
    TemplateValue.PROFILE_WATCH
)
public class ProfileWatchJobDetails implements JobDetails {

    @TemplateField(
	    label = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    label = "Total watch hours",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 24,
	    maxFieldLabel = "max no. of hours",
	    minFieldLabel = "min no. of hours"
    )
    private Integer durationHours;

    @TemplateField(
	    label = "Total pay for this job",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    payFieldType = PayFieldTypeValue.TOTAL,
	    maxFieldLabel = "max pay",
	    minFieldLabel = "min pay"
    )
    private Long pay;
}
