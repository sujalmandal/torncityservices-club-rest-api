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
    TemplateValue.EMPLOYEE
)
public class EmployeeJobDetails implements JobDetails {

    /*
     * API: https://api.torn.com/torn/?selections=companies&key=<API_KEY>
     * 
     * filter response with JMESPath 'companies.[*][0][*].name'
     */
    @TemplateField(
	    label = "Company type",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Hair Salon", "Law Firm", "Flower Shop",
		    "Car Dealership", "Clothing Store", "Gun Shop", "Game Shop", "Candle Shop", "Toy Shop",
		    "Adult Novelties", "Cyber Cafe", "Grocery Store", "Theater", "Sweet Shop", "Cruise Line",
		    "Television Network", "Zoo", "Firework Stand", "Property Broker", "Furniture Store", "Gas Station",
		    "Music Store", "Nightclub", "Pub", "Gents Strip Club", "Restaurant", "Oil Rig", "Fitness Center",
		    "Mechanic Shop", "Amusement Park", "Lingerie Store", "Meat Warehouse", "Farm",
		    "Software Corporation", "Ladies Strip Club", "Private Security Firm", "Mining Corporation",
		    "Detective Agency", "Logistics Management" },
	    labelRequest = "Company type %s wants to work in",
	    labelOffer = "Company type in which work is offered"
    )
    private String companyType;

    @TemplateField(
	    label = "Company star/level",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 10,
	    labelRequest = "Company level %s wants to work in",
	    labelOffer = "Company level in which work is offered",
	    maxFieldLabel = "maximum star/level",
	    minFieldLabel = "minimum star/level"
    )
    private Integer companyStar;

    @TemplateField(
	    label = "End",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    labelRequest = "Endurance stat of %s",
	    labelOffer = "Minimum endurance required for this vacancy",
	    maxFieldLabel = "maximum end",
	    minFieldLabel = "minimum end"
    )
    private Integer endurance;

    @TemplateField(
	    label = "Man",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    labelRequest = "Manual stat of %s",
	    labelOffer = "Minimum manual required for this vacancy",
	    maxFieldLabel = "maximum man",
	    minFieldLabel = "minimum man"
    )
    private Integer manual;

    @TemplateField(
	    label = "Int",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    labelRequest = "Intelligence stat of %s",
	    labelOffer = "Minimum intelligence required for this vacancy",
	    maxFieldLabel = "maximum int",
	    minFieldLabel = "minimum int"
    )
    private Integer intelligence;

    @TemplateField(
	    label = "Daily pay",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 0,
	    maxValue = 5_000_000,
	    payFieldType = PayFieldTypeValue.PER_ACTION,
	    labelRequest = "Minimum salary %s wants",
	    labelOffer = "Maximum salary %s offered for this vacancy",
	    maxFieldLabel = "maximum daily salary",
	    minFieldLabel = "minimum daily salary"
    )
    private Long pay;

}