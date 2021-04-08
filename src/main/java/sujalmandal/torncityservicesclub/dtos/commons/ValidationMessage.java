package sujalmandal.torncityservicesclub.dtos.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationMessage {
    private String fieldName;
    private String message;
}
