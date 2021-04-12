package sujalmandal.torncityservicesclub.constants;

public enum JobFieldNames {
    IS_DELETED(
	    "isDeleted"
    ), STATUS(
	    "status"
    ), LISTED_BY_PLAYER_ID(
	    "listedByPlayerId"
    ), ACCEPTED_BY_PLAYER_ID(
	    "acceptedByPlayerId"
    ), POSTED_DATE(
	    "postedDate"
    ), ACCEPTED_DATE(
	    "acceptedDate"
    ), FINISHED_DATE(
	    "finishedDate"
    ), SERVICE_TYPE(
	    "serviceType"
    ), JOB_DETAILS(
	    "jobDetails"
    ), FILTER_TEMPLATE_NAME(
	    "filterTemplateName"
    ), ID(
	    "seqId"
    );

    private String fieldName;

    private JobFieldNames(String fieldName) {
	this.fieldName = fieldName;
    }

    @Override
    public String toString() {
	return this.fieldName;
    }

}
