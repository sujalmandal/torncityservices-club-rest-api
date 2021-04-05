package sujalmandal.torncityservicesclub.enums;

public enum FormFieldTypeValue {
    TEXT("text"), NUMBER("number"), CHECKBOX("checkbox"), SELECT("select");

    String value;

    private FormFieldTypeValue(String value) {
	this.value = value;
    }

    @Override
    public String toString() {
	return this.value;
    }
}