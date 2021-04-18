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
    TemplateValue.REVIVE_CONTRACT_REQUEST
)
public class ReviveContractServiceRequest extends ServiceDetail {

    @TemplateField(
	    formLabel = "Faction for which you are buying revives",
	    optional = true,
	    isSearchable = false
    )
    private String factionName;

    @TemplateField(
	    formLabel = "Total revives you wish to buy",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 5,
	    optional = true,
	    filterMaxFieldLabel = "max. no. of revives",
	    filterMinFieldLabel = "min. no. of revives"
    )
    private Integer totalRevives;

    @TemplateField(
	    formLabel = "Maximum price you are willing to pay per revive",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 250_000,
	    maxValue = 3_000_000,
	    filterMaxFieldLabel = "max. pay per revive",
	    filterMinFieldLabel = "min. pay per revive"
    )
    private Long pay;
}
