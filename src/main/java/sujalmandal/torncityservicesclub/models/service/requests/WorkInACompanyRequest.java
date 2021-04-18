package sujalmandal.torncityservicesclub.models.service.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;

@Data
@EqualsAndHashCode(
	callSuper = false
)
@Templatized(
    TemplateValue.JOB_REQUEST
)
public class WorkInACompanyRequest extends ServiceDetail {

    /*
     * API: https://api.torn.com/torn/?selections=companies&key=<API_KEY>
     * 
     * filter response with JMESPath 'companies.[*][0][*].name'
     */
    @TemplateField(
	    formLabel = "Type of the company in which you wish to work",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Hair Salon", "Law Firm", "Flower Shop",
		    "Car Dealership", "Clothing Store", "Gun Shop", "Game Shop", "Candle Shop", "Toy Shop",
		    "Adult Novelties", "Cyber Cafe", "Grocery Store", "Theater", "Sweet Shop", "Cruise Line",
		    "Television Network", "Zoo", "Firework Stand", "Property Broker", "Furniture Store", "Gas Station",
		    "Music Store", "Nightclub", "Pub", "Gents Strip Club", "Restaurant", "Oil Rig", "Fitness Center",
		    "Mechanic Shop", "Amusement Park", "Lingerie Store", "Meat Warehouse", "Farm",
		    "Software Corporation", "Ladies Strip Club", "Private Security Firm", "Mining Corporation",
		    "Detective Agency", "Logistics Management" },
	    viewLabel = "Company type %s wants to work in"
    )
    private String companyType;

    @TemplateField(
	    formLabel = "Level of the Company in which you wish to work",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 10,
	    viewLabel = "Company level %s wants to work in",
	    filterMaxFieldLabel = "maximum star/level",
	    filterMinFieldLabel = "minimum star/level"
    )
    private Integer companyStar;

    @TemplateField(
	    formLabel = "Your total endurance",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Endurance stat of %s",
	    filterMaxFieldLabel = "maximum end",
	    filterMinFieldLabel = "minimum end"
    )
    private Integer endurance;

    @TemplateField(
	    formLabel = "Your total manual",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Manual stat of %s",
	    filterMaxFieldLabel = "maximum man",
	    filterMinFieldLabel = "minimum man"
    )
    private Integer manual;

    @TemplateField(
	    formLabel = "Your total intelligence",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Intelligence stat of %s",
	    filterMaxFieldLabel = "maximum int",
	    filterMinFieldLabel = "minimum int"
    )
    private Integer intelligence;

    @TemplateField(
	    formLabel = "Minimum daily salary you want in your new job",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 0,
	    maxValue = 5_000_000,
	    viewLabel = "Minimum salary %s wants",
	    filterMaxFieldLabel = "maximum daily salary",
	    filterMinFieldLabel = "minimum daily salary"
    )
    private Long pay;

}