package sujalmandal.torncityservicesclub.models.service.offers;

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
    TemplateValue.HOSPITALIZE_OFFER
)
public class HospitalizeServiceOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Total number of hospitalization you want to sell",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max. no. of hospitalizations",
	    filterMinFieldLabel = "min. no. of hospitalizations"
    )
    private Integer totalHospitalizations;

    @TemplateField(
	    formLabel = "How much you wish to charge per hospitalization",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 100_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max. charge per hospitalization",
	    filterMinFieldLabel = "min. charge per hospitalization"

    )
    private Long pay;
}
