package sujalmandal.torncityservicesclub.constants;

public enum MongoCollections {

    SERVICE_DETAIL_TEMPLATE(
	    "ServiceDetailTemplate"
    ), MONGO_SEQUENCE(
	    "MongoSequence"
    );

    private String value;

    MongoCollections(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return this.value;
    }
}
