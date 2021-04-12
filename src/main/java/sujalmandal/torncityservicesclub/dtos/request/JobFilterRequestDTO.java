package sujalmandal.torncityservicesclub.dtos.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sujalmandal.torncityservicesclub.constants.JobStatus;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.dtos.commons.FilterFieldDTO;

@Getter
@Setter
@EqualsAndHashCode(
	callSuper = true
)
@ToString
public class JobFilterRequestDTO extends RequestDTO {

    private List<Long> jobIds;
    private ServiceTypeValue serviceType;
    private Integer postedXDaysAgo = 3;
    private List<FilterFieldDTO> filterFields;
    private String filterTemplateName;
    private JobStatus jobStatus = JobStatus.AVAILABLE;
    private int pageSize = 12;
    private int pageNumber = 1;
    @JsonIgnore
    private boolean fetchDetails = Boolean.FALSE;

}