package sujalmandal.torncityservicesclub.dtos.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JobDetailResponseDTO {
    List<JobDetailFieldResponseDTO> fields = new ArrayList<JobDetailFieldResponseDTO>();
}