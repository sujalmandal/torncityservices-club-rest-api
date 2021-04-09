package sujalmandal.torncityservicesclub.dtos.request;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Getter
@Setter
public class CreateJobRequestDTO extends RequestDTO {
    private ServiceTypeValue serviceType;
    private String templateName;
    private HashMap<String, Object> jobDetails;

    @Override
    public String toString() {
	return "CreateJobRequestDTO [serviceType=" + serviceType + ", templateName=" + templateName + ", jobDetails="
		+ jobDetails + ", apiKey" + getApiKey() + ", fingerprint=" + fingerprint + "]";
    }

}