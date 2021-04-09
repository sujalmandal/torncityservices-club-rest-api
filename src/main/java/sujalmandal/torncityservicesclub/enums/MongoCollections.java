package sujalmandal.torncityservicesclub.enums;

public enum MongoCollections {

    JOB("Job"), MONGO_SEQUENCE("MongoSequence");

    private String value;

    MongoCollections(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return this.value;
    }
}
