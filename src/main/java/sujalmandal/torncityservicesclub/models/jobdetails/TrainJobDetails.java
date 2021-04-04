package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@JobDetailTemplate(JobDetailTemplateValue.TRAINS)
public class TrainJobDetails implements JobDetails {

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("How many trains in total")
    @FilterableField(label = "total spies", maxFieldLabel = "maximum no. of spies", minFieldLabel = "minimum no. of spies", limit = "25")
    private Integer totalTrains;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Money per train")
    @FilterableField(label = "total pay per train", maxFieldLabel = "maximum pay per train", minFieldLabel = "minimum pay per train", limit = "750_000")
    private Integer amountPerTrain;
}