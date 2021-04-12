package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
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
@ToString
@Template(
    TemplateValue.HOSPITALIZE
)
public class HospitalizeJobDetails implements JobDetails {

    @TemplateField(
	    labelCommon = "Torn id of the player who has to be attacked",
	    serviceType = ServiceTypeValue.REQUEST,
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    labelCommon = "Total number of times to hospitalize a target",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    maxFieldLabel = "maximum no. of hospitalizations",
	    minFieldLabel = "minimum no. of hospitalizations"
    )
    private Integer totalHospitalizations;

    @TemplateField(
	    labelCommon = "Pay per hospitalization for this job",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 100_000,
	    maxValue = 5_000_000,

	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    payOnServiceType = ServiceTypeValue.REQUEST,

	    maxFieldLabel = "max pay per hospitalization",
	    minFieldLabel = "min pay per hospitalization"

    )
    private Long pay;
}
