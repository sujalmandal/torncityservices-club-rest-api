package sujalmandal.torncityservicesclub.models.service.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Templatized(
    TemplateValue.PROFILE_WATCH_REQUEST
)
public class ProfileWatchJobDetails extends ServiceDetail {

    @TemplateField(
	    formLabel = "Torn id of the player who has to be watched",
	    optional = true,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    formLabel = "Total watch hours",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 24,
	    filterMaxFieldLabel = "max. no. of hours",
	    filterMinFieldLabel = "min. no. of hours"
    )
    private Integer durationHours;

    @TemplateField(
	    formLabel = "Maximum you are willing to pay for this job",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 1_000_000,
	    maxValue = 100_000_000,
	    filterMaxFieldLabel = "max. total  pay",
	    filterMinFieldLabel = "min. total pay"
    )
    private Long pay;
}
