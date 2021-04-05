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
@GenerateTemplate(JobDetailTemplateValue.EMPLOYEE)
public class EmployeeJobDetails implements JobDetails {

    @HighlightWhen
    @FormField(label = "Company type")
    @FilterableField(label = "company type")
    private String companyType;

    @HighlightWhen
    @FormField(label = "Company star/level", type = FormFieldTypeValue.NUMBER, minValue = "1", maxValue = "10")
    @FilterableField(label = "company star/level", maxFieldLabel = "maximum star/level", minFieldLabel = "minimum star/level")
    private Integer companyStar;

    @HighlightWhen
    @FormField(label = "Daily pay", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, maxValue = "5_000_000")
    @FilterableField(label = "daily salary", maxFieldLabel = "maximum daily salary", minFieldLabel = "minimum daily salary")
    private Integer pay;

    @FormField(label = "End", type = FormFieldTypeValue.NUMBER, maxValue = "500_000")
    @FilterableField(label = "End", maxFieldLabel = "maximum end", minFieldLabel = "minimum end")
    private Integer endurance;

    @FormField(label = "Man", type = FormFieldTypeValue.NUMBER, maxValue = "300_000")
    @FilterableField(label = "Man", maxFieldLabel = "maximum man", minFieldLabel = "minimum man")
    private Integer manual;

    @FormField(label = "Int", type = FormFieldTypeValue.NUMBER, maxValue = "300_000")
    @FilterableField(label = "Int", maxFieldLabel = "maximum int", minFieldLabel = "minimum int")
    private Integer intelligence;

}