package sujalmandal.torncityservicesclub.constants;

public enum FieldTypeValue {
    TEXT("text"), NUMBER("number"), CHECKBOX("checkbox"), SELECT("select");

    String value;

    private FieldTypeValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return this.value;
    }
}