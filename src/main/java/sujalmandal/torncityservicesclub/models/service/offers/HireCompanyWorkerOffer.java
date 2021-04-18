package sujalmandal.torncityservicesclub.models.service.offers;

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
    TemplateValue.HIRE_OFFER
)
public class HireCompanyWorkerOffer extends ServiceDetail {

    /*
     * API: https://api.torn.com/torn/?selections=companies&key=<API_KEY>
     * 
     * filter response with JMESPath 'companies.[*][0][*].name'
     */
    @TemplateField(
	    formLabel = "Company type for which you are hiring",
	    type = FieldTypeValue.SELECT,
	    options = { AppConstants.SELECT_DUMMY_OPTION, "All", "Hair Salon", "Law Firm", "Flower Shop",
		    "Car Dealership", "Clothing Store", "Gun Shop", "Game Shop", "Candle Shop", "Toy Shop",
		    "Adult Novelties", "Cyber Cafe", "Grocery Store", "Theater", "Sweet Shop", "Cruise Line",
		    "Television Network", "Zoo", "Firework Stand", "Property Broker", "Furniture Store", "Gas Station",
		    "Music Store", "Nightclub", "Pub", "Gents Strip Club", "Restaurant", "Oil Rig", "Fitness Center",
		    "Mechanic Shop", "Amusement Park", "Lingerie Store", "Meat Warehouse", "Farm",
		    "Software Corporation", "Ladies Strip Club", "Private Security Firm", "Mining Corporation",
		    "Detective Agency", "Logistics Management" },
	    viewLabel = "Company type %s is hiring for"
    )
    private String companyType;

    @TemplateField(
	    formLabel = "Your Company's star/level",
	    type = FieldTypeValue.NUMBER,
	    minValue = 1,
	    maxValue = 10,
	    viewLabel = "Company level in which work is offered",
	    filterMaxFieldLabel = "maximum star/level",
	    filterMinFieldLabel = "minimum star/level"
    )
    private Integer companyStar;

    @TemplateField(
	    formLabel = "Min. End stat you want your employee to have",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Min. endurance stat %s requires for this vacancy",
	    filterMaxFieldLabel = "maximum end",
	    filterMinFieldLabel = "minimum end"
    )
    private Integer endurance;

    @TemplateField(
	    formLabel = "Min. Man stat you want your employee to have",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Min. manual stat %s requires for this vacancy",
	    filterMaxFieldLabel = "maximum man",
	    filterMinFieldLabel = "minimum man"
    )
    private Integer manual;

    @TemplateField(
	    formLabel = "Min. Int stat you want your employee to have",
	    type = FieldTypeValue.NUMBER,
	    minValue = 100,
	    maxValue = 500_000,
	    viewLabel = "Min. intelligence stat %s requires for this vacancy",
	    filterMaxFieldLabel = "maximum int",
	    filterMinFieldLabel = "minimum int"
    )
    private Integer intelligence;

    @TemplateField(
	    formLabel = "Mininum salary you are willing to pay",
	    type = FieldTypeValue.NUMBER,
	    formatter = FieldFormatValue.CURRENCY,
	    minValue = 0,
	    maxValue = 5_000_000,
	    viewLabel = "Maximum salary %s offered for this vacancy",
	    filterMaxFieldLabel = "maximum daily salary",
	    filterMinFieldLabel = "minimum daily salary"
    )
    private Long pay;

}