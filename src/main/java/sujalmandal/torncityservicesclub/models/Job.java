package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.enums.JobType;

@Data
@Document(collection = "Job")
@NoArgsConstructor
public class Job {
    @Id
    private String id;
    private JobType jobType;
    private JobStatus status = JobStatus.AVAILABLE;
    private Boolean isDeleted = Boolean.FALSE;
    private int amount;
    private Long pay;
    private String listedByPlayerId;
    private String acceptedByPlayerId;
    private LocalDateTime postedDate;
    private LocalDateTime acceptedDate;
    private LocalDateTime finishedDate;
}