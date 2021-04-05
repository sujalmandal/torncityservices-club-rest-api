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
@GenerateTemplate(JobDetailTemplateValue.TRAINS)
public class TrainJobDetails implements JobDetails {

    @FormField(label = "How many trains in total", type = FormFieldTypeValue.NUMBER, minValue = "50", maxValue = "500", defaultValue = "50")
    @FilterableField(label = "total spies", maxFieldLabel = "maximum no. of spies", minFieldLabel = "minimum no. of spies")
    private Integer totalTrains;

    @HighlightWhen
    @FormField(label = "Money per train", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY)
    @FilterableField(label = "total pay per train", maxFieldLabel = "maximum pay per train", minFieldLabel = "minimum pay per train")
    private Integer amountPerTrain;
}