package sujalmandal.torncityservicesclub.dtos.response;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.models.Job;

@Data
public class JobFilterResponseDTO {

    List<Job> jobs;
    private int pageSize=25;
    private int pageNumber=0;
    
}
