package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.RENT)
public class RentJobDetails implements JobDetails {

    @HighlightWhen(ServiceTypeValue.REQUEST)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total happy")
    @FilterableField(label = "total happy for the property", maxFieldLabel = "maximum happy", minFieldLabel = "minimum happy", limit = "5000")
    private String happy;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total number of days for rent")
    @FilterableField(label = "total rent duration in days", maxFieldLabel = "maximum rented days", minFieldLabel = "minimum rented days", limit = "90")
    private Integer durationInDays;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen(ServiceTypeValue.OFFER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total rent")
    @FilterableField(label = "total rent", maxFieldLabel = "maximum rent", minFieldLabel = "minimum rent", limit = "100_000_000")
    private Integer rent;

}