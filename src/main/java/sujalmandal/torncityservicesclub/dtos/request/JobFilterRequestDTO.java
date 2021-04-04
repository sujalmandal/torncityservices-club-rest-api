package sujalmandal.torncityservicesclub.dtos.request;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sujalmandal.torncityservicesclub.dtos.commons.FilterFieldDTO;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class JobFilterRequestDTO extends RequestDTO {

    private ServiceTypeValue serviceType;
    private Integer postedXDaysAgo;
    private List<FilterFieldDTO> filterFields;
    private int pageSize = 25;
    private int pageNumber = 0;

}