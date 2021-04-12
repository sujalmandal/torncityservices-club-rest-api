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
    TemplateValue.REVIVE_FACTION
)
public class FactionReviveJobDetails implements JobDetails {

    @TemplateField(
	    label = "Faction to revive",
	    serviceType = ServiceTypeValue.REQUEST,
	    optional = true,
	    isSearchable = false
    )
    private String factionName;

    @TemplateField(
	    label = "Total revives",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true,
	    maxFieldLabel = "max revives",
	    minFieldLabel = "min revives"
    )
    private Integer totalRevives;

    @TemplateField(
	    label = "Duration over which revives need to be done",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true,
	    maxFieldLabel = "max days",
	    minFieldLabel = "min days"
    )
    private Integer durationDays;

    @TemplateField(
	    label = "Pay per revive",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 250_000,
	    maxValue = 3_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "max pay",
	    minFieldLabel = "min pay"
    )
    private Long pay;
}
