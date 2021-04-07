package sujalmandal.torncityservicesclub.enums;

public enum JobFilterCriteriaField {
    IS_DELETED("isDeleted"), STATUS("status"), LISTED_BY_PLAYER_ID("listedByPlayerId"),
    ACCEPTED_BY_PLAYER_ID("acceptedByPlayerId"), POSTED_DATE("postedDate"), ACCEPTED_DATE("acceptedDate"),
    FINISHED_DATE("finishedDate"), SERVICE_TYPE("serviceType"), JOB_DETAILS("jobDetails"),
    FILTER_TEMPLATE_NAME("filterTemplateName");

    private String fieldName;

    private JobFilterCriteriaField(String fieldName) {
	this.fieldName = fieldName;
    }

    @Override
    public String toString() {
	return this.fieldName;
    }

}
