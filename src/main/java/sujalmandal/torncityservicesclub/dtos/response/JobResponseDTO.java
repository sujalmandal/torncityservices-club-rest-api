package sujalmandal.torncityservicesclub.dtos.response;

import lombok.Data;
import sujalmandal.torncityservicesclub.dtos.commons.TemplateMetaInfo;

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

    private TemplateMetaInfo templateMetaInfo;

    private ServiceDetailResponseDTO details;
}
