package sujalmandal.torncityservicesclub.models.service.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
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

@ToString
@Templatized(
    TemplateValue.HOSPITALIZE_REQUEST
)
public class HospitalizeJobDetails extends ServiceDetail {

    @TemplateField(
	    formLabel = "Torn id of the player who has to be hospitalized",
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    formLabel = "Total number of times to hospitalize a target",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max. no. of hospitalizations",
	    filterMinFieldLabel = "min. no. of hospitalizations"
    )
    private Integer totalHospitalizations;

    @TemplateField(
	    formLabel = "Minimum pay you require for each hospitalization",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 100_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max pay per hospitalization",
	    filterMinFieldLabel = "min pay per hospitalization"
    )
    private Long pay;
}
