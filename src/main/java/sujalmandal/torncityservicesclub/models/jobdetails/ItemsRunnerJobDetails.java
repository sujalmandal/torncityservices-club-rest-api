package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Template(
    TemplateValue.ITEMS_RUNNER
)
public class ItemsRunnerJobDetails implements JobDetails {

    @TemplateField(
	    label = "Type of items to run",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Temporaries", "Plushies", "Flowers",
		    "High value items", "Weapons" }
    )
    private String itemType;

    @TemplateField(
	    label = "Total pay per trip",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 500_000,
	    maxValue = 50_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    maxFieldLabel = "max pay per trip",
	    minFieldLabel = "min pay per trip"
    )
    private Long payPerTrip;

}