package sujalmandal.torncityservicesclub.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class JobFilterRequestDTO {
    
    private List<ServiceTypeValue> serviceTypes;
    private Integer amountGreaterThan;
    private Long payGreaterThan;
    private Integer amountLessThan;
    private Long payLessThan;
    private LocalDateTime postedDateAfter;
    private LocalDateTime postedDateBefore;
    private int pageSize=25;
    private int pageNumber=0;

}
