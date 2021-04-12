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
    TemplateValue.DIRTY_BOMB_A_FACTION
)
public class DirtyBombJobDetails implements JobDetails {

    @TemplateField(
	    label = "Faction to attack",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String factionName;

    @TemplateField(
	    label = "Pay for this job (better be good)",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 100_000_000,
	    maxValue = 5_000_000_000L,
	    payFieldType = PayFieldTypeValue.TOTAL,
	    maxFieldLabel = "maximum pay",
	    minFieldLabel = "minimum pay"
    )
    private Long pay;

}