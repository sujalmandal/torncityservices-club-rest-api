package sujalmandal.torncityservicesclub.dtos.response;

import lombok.Data;

@Data
public class JobDetailFieldResponseDTO {
    private String label;
    private Object value;
    private String format;
    private String type;
    private String labelRequest;
    private String labelOffer;
}