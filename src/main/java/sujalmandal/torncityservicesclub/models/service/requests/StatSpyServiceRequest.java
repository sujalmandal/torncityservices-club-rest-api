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
    TemplateValue.STAT_SPY_REQUEST
)
public class StatSpyServiceRequest extends ServiceDetail {

    @TemplateField(
	    formLabel = "Torn id of the player to spy on",
	    isSearchable = false
    )
    private String targetPlayerId;

    @TemplateField(
	    formLabel = "Total spies you wish to buy",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 50,
	    filterMaxFieldLabel = "max no. of spies",
	    filterMinFieldLabel = "min no. of spies"
    )
    private Integer totalSpy;

    @TemplateField(
	    formLabel = "Minimum price you are willing to pay for each spy",
	    formatter = FieldFormatValue.CURRENCY,
	    type = FieldTypeValue.NUMBER,
	    minValue = 750_000,
	    maxValue = 5_000_000,
	    filterMaxFieldLabel = "max. pay per spy",
	    filterMinFieldLabel = "min. pay per spy"
    )
    private Long pay;
}