package sujalmandal.torncityservicesclub.models.service.offers;

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
    TemplateValue.ITEMS_RUN_OFFER
)
public class ItemsRunOffer extends ServiceDetail {

    @TemplateField(
	    formLabel = "Type of items to run",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Temporaries", "Plushies", "Flowers",
		    "High value items", "Weapons" }
    )
    private String itemType;

    @TemplateField(
	    formLabel = "Total pay per trip",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 500_000,
	    maxValue = 50_000_000,
	    filterMaxFieldLabel = "max pay per trip",
	    filterMinFieldLabel = "min pay per trip"
    )
    private Long costPerTrip;

}