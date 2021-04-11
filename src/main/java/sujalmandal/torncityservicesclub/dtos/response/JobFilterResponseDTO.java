package sujalmandal.torncityservicesclub.dtos.response;

import java.util.List;

import lombok.Data;

@Data
public class JobFilterResponseDTO {

    List<JobResponseDTO> jobs;
    private long totalSize = 0;
    private long pageSize = 0;
    private long pageNumber = 0;
    private long totalPages = 0;

}