package sujalmandal.torncityservicesclub.dtos.response;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;

@Data
public class JobResponseDTO {

    private Long seqId;
    private String listedByPlayerId;
    private String listedByPlayerName;
    private String acceptedByPlayerId;
    private String acceptedByPlayerName;

    private String postedDate;
    private String acceptedDate;
    private String finishedDate;
    private String templateName;
    private String templateLabel;

    private JobDetailResponseDTO details;

    private ServiceTypeValue serviceType;
    private ServiceTypeValue payOnServiceType;
    private Long payPerAction;
    private Long totalPay;
}
