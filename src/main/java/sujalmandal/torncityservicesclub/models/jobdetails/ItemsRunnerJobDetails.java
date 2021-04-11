package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.AppConstants;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.PayFieldType;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@GenerateTemplate(
    JobDetailTemplateValue.ITEMS_RUNNER
)
public class ItemsRunnerJobDetails implements JobDetails {

    @FormField(
	    label = "Type of items to run",
	    type = FormFieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Temporaries", "Plushies", "Flowers",
		    "High value items", "Weapons" }
    )
    @FilterableField(
	    label = "Type of the item"
    )
    private String itemType;

    @FormField(
	    label = "Total pay per trip",
	    type = FormFieldTypeValue.NUMBER,
	    formatter = FieldFormatterValue.CURRENCY,
	    minValue = 500_000,
	    maxValue = 50_000_000,
	    payFieldType = PayFieldType.PER_ACTION
    )
    @FilterableField(
	    label = "total pay per trip",
	    maxFieldLabel = "max pay per trip",
	    minFieldLabel = "min pay per trip"
    )
    private Long payPerTrip;

}