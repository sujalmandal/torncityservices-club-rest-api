package sujalmandal.torncityservicesclub.models.service.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Templatized(
    TemplateValue.ITEMS_RUN_REQUEST
)
public class ItemsRunnerJobDetails extends ServiceDetail {

    @TemplateField(
	    formLabel = "Type of item you want the runner to run",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Temporaries", "Plushies", "Flowers",
		    "High value items", "Weapons" }
    )
    private String itemType;

    @TemplateField(
	    formLabel = "Maximum you are willing to pay per trip",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 500_000,
	    maxValue = 50_000_000,
	    filterMaxFieldLabel = "max. pay per trip",
	    filterMinFieldLabel = "min. pay per trip"
    )
    private Long payPerTrip;

}