package sujalmandal.torncityservicesclub.enums;

public enum JobDetailFieldTypeValue {
    TEXT("text"), NUMBER("number"), CHECKBOX("checkbox");

    String value;

    private JobDetailFieldTypeValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return this.value;
    }
}