package sujalmandal.torncityservicesclub.enums;

public enum AppConstants {

    NUMBER_TYPE_FIELD_MIN_PREFIX("min"), NUMBER_TYPE_FIELD_MAX_PREFIX("max"),
    JOB_DETAIL_IMPL_SEARCH_PACKAGE("sujalmandal.torncityservicesclub.models.jobdetails"),
    MONEY_EVENT_PATTERN("\\$[0-9]+"), TORN_CASH_PAYMENT_REDIRECT_URL_STUB("https://www.torn.com/sendcash.php#/XID=%s"),
    DD_MM_YYYY_DATE_FORMAT("dd-MM-yyyy"), JOB_DETAILS("jobDetails");

    private String fieldName;

    private AppConstants(String fieldName) {
	this.fieldName = fieldName;
    }

    @Override
    public String toString() {
	return this.fieldName;
    }
}
