package sujalmandal.torncityservicesclub.dtos.response;

import lombok.Data;

@Data
public class ServiceDetailFieldResponseDTO {
    private String formLabel;
    private Object value;
    private String format;
    private String type;
    private String viewLabel;
}