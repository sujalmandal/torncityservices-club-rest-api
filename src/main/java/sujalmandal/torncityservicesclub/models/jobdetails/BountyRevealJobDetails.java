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
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
@EqualsAndHashCode(callSuper = false)
@GenerateTemplate(JobDetailTemplateValue.BOUNTY_REVEAL)
public class BountyRevealJobDetails implements JobDetails {

    @FormField(label = "Player whose bounty has to be revealed", serviceType = ServiceTypeValue.REQUEST)
    private String playerId;

    @HighlightWhen
    @FormField(label = "Cost of a bounty reveal", formatter = FieldFormatterValue.CURRENCY, type = FormFieldTypeValue.NUMBER, maxValue = "100_000_000")
    @FilterableField(label = "total pay", maxFieldLabel = "maximum pay", minFieldLabel = "minimum pay")
    private Integer pay;
}
