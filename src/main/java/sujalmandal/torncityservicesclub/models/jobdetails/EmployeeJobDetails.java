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
	
	/*
	 * API: https://api.torn.com/torn/?selections=companies&key=<API_KEY>
	 * 
	 * filter response with JMESPath 'companies.[*][0][*].name'
	 */
	@FormField(label = "Company type", type = FormFieldTypeValue.SELECT, options = {
			"Hair Salon", "Law Firm", "Flower Shop", "Car Dealership",
			"Clothing Store", "Gun Shop", "Game Shop", "Candle Shop",
			"Toy Shop", "Adult Novelties", "Cyber Cafe", "Grocery Store",
			"Theater", "Sweet Shop", "Cruise Line", "Television Network", "Zoo",
			"Firework Stand", "Property Broker", "Furniture Store",
			"Gas Station", "Music Store", "Nightclub", "Pub",
			"Gents Strip Club", "Restaurant", "Oil Rig", "Fitness Center",
			"Mechanic Shop", "Amusement Park", "Lingerie Store",
			"Meat Warehouse", "Farm", "Software Corporation",
			"Ladies Strip Club", "Private Security Firm", "Mining Corporation",
			"Detective Agency",
			"Logistics Management"}, defaultValue = "Candle Shop")
	@FilterableField(label = "company type")
	private String companyType;
	
	@HighlightWhen
	@FormField(label = "Company star/level", type = FormFieldTypeValue.NUMBER, minValue = "1", maxValue = "10")
	@FilterableField(label = "company star/level", maxFieldLabel = "maximum star/level", minFieldLabel = "minimum star/level")
	private Integer companyStar;
	
	@HighlightWhen
	@FormField(label = "Daily pay", type = FormFieldTypeValue.NUMBER, formatter = FieldFormatterValue.CURRENCY, minValue = "0", maxValue = "5_000_000")
	@FilterableField(label = "daily salary", maxFieldLabel = "maximum daily salary", minFieldLabel = "minimum daily salary")
	private Integer pay;
	
	@FormField(label = "End", type = FormFieldTypeValue.NUMBER, minValue = "100", maxValue = "500_000")
	@FilterableField(label = "End", maxFieldLabel = "maximum end", minFieldLabel = "minimum end")
	private Integer endurance;
	
	@FormField(label = "Man", type = FormFieldTypeValue.NUMBER, minValue = "100", maxValue = "500_000")
	@FilterableField(label = "Man", maxFieldLabel = "maximum man", minFieldLabel = "minimum man")
	private Integer manual;
	
	@FormField(label = "Int", type = FormFieldTypeValue.NUMBER, minValue = "100", maxValue = "500_000")
	@FilterableField(label = "Int", maxFieldLabel = "maximum int", minFieldLabel = "minimum int")
	private Integer intelligence;
	
}