package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.ITEMS_RUNNER)
public class ItemsRunnerJobDetails implements JobDetails {
	
	@FormField(label = "Type of items to run", type = FormFieldTypeValue.SELECT, options = {
			"Temporaries", "Plushies", "Flowers", "High value items",
			"Weapons"}, defaultValue = "Plushies")
	@FilterableField(label = "Type of the item")
	private String itemType;
	
	@HighlightWhen
	@FormField(label = "Total pay per trip", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, minValue = "500_000", maxValue = "50_000_000")
	@FilterableField(label = "total pay per trip", maxFieldLabel = "maximum pay per trip", minFieldLabel = "minimum pay per trip")
	private Integer payPerTrip;
	
}