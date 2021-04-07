package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import sujalmandal.torncityservicesclub.dtos.response.JobResponseDTO;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.utils.AppUtils;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Data
@Document(collection = "Job")
@NoArgsConstructor
public class Job {
    @Id
    private String id;
    private JobStatus status = JobStatus.AVAILABLE;
    private Boolean isDeleted = Boolean.FALSE;

    private String listedByPlayerId;
    private String listedByPlayerName;
    private String acceptedByPlayerId;
    private String acceptedByPlayerName;

    private LocalDateTime postedDate;
    private LocalDateTime acceptedDate;
    private LocalDateTime finishedDate;
    private String templateName;
    private String filterTemplateName;
    private JobDetails jobDetails;
    private ServiceTypeValue serviceType;

    public JobResponseDTO toJobResponseDTO() {
	JobResponseDTO responseDTO = new JobResponseDTO();
	PojoUtils.map(this, responseDTO);
	responseDTO.setPostedDate(AppUtils.dateToDD_MM_YYYYString(this.postedDate));
	responseDTO.setAcceptedDate(AppUtils.dateToDD_MM_YYYYString(this.acceptedDate));
	responseDTO.setFinishedDate(AppUtils.dateToDD_MM_YYYYString(this.finishedDate));
	return responseDTO;
    }
}