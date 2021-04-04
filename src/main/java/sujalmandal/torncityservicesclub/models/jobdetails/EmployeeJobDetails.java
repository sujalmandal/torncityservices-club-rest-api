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
@JobDetailTemplate(JobDetailTemplateValue.EMPLOYEE)
public class EmployeeJobDetails implements JobDetails {

    @HighlightWhen
    @JobDetailFieldLabel("Company type")
    @JobDetailFieldType(JobDetailFieldTypeValue.TEXT)
    @FilterableField(label = "company type")
    private String companyType;

    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Company star/level")
    @FilterableField(label = "company star/level", maxFieldLabel = "maximum star/level", minFieldLabel = "minimum star/level", limit = "10")
    private Integer companyStar;

    @FieldFormatter(FieldFormatterValue.CURRENCY)
    @HighlightWhen
    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Daily pay")
    @FilterableField(label = "daily salary", maxFieldLabel = "maximum daily salary", minFieldLabel = "minimum daily salary", limit = "5_000_000")
    private Integer pay;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("End")
    @FilterableField(label = "End", maxFieldLabel = "maximum end", minFieldLabel = "minimum end", limit = "300_000")
    private Integer endurance;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Man")
    @FilterableField(label = "Man", maxFieldLabel = "maximum man", minFieldLabel = "minimum man", limit = "300_000")
    private Integer manual;

    @JobDetailFieldType(JobDetailFieldTypeValue.NUMBER)
    @JobDetailFieldLabel("Int")
    @FilterableField(label = "Int", maxFieldLabel = "maximum int", minFieldLabel = "minimum int", limit = "300_000")
    private Integer intelligence;

}
