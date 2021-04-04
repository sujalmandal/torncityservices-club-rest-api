package sujalmandal.torncityservicesclub.models.jobdetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplate;
import sujalmandal.torncityservicesclub.annotations.ServiceType;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@JobDetailTemplate(JobDetailTemplateValue.STAT_SPY)
public class StatSpyJobDetails implements JobDetails {

    @ServiceType(ServiceTypeValue.REQUEST)
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @JobDetailFieldLabel("Torn id of the player to spy on")
    private String targetPlayerId;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @ServiceType
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total pay")
    @FilterableField(label = "total pay per spy", maxFieldLabel = "maximum pay per spy", minFieldLabel = "minimum pay per spy", limit = "5_000_000")
    private String pay;

    @ServiceType(ServiceTypeValue.OFFER)
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Total spies to sell")
    @FilterableField(label = "total spies", maxFieldLabel = "maximum no. of spies", minFieldLabel = "minimum no. of spies", limit = "25")
    private Integer totalSpy;

}