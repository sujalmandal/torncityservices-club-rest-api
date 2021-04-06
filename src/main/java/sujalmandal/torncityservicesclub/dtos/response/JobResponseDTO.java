package sujalmandal.torncityservicesclub.dtos.response;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
public class JobResponseDTO {
    private String listedByPlayerId;
    private String listedByPlayerName;
    private String acceptedByPlayerId;
    private String acceptedByPlayerName;

    private String postedDate;
    private String acceptedDate;
    private String finishedDate;
    private String templateName;
    private JobDetails jobDetails;
    private ServiceTypeValue serviceType;
}
