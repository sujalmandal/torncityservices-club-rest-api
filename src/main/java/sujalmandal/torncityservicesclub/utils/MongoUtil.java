package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.constants.AppConstants.JOB_DETAILS;
import static sujalmandal.torncityservicesclub.constants.AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX;
import static sujalmandal.torncityservicesclub.constants.AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX;
import static sujalmandal.torncityservicesclub.constants.JobFieldNames.FILTER_TEMPLATE_NAME;
import static sujalmandal.torncityservicesclub.constants.JobFieldNames.IS_DELETED;
import static sujalmandal.torncityservicesclub.constants.JobFieldNames.POSTED_DATE;
import static sujalmandal.torncityservicesclub.constants.JobFieldNames.SERVICE_TYPE;
import static sujalmandal.torncityservicesclub.constants.JobFieldNames.STATUS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.JobFieldNames;
import sujalmandal.torncityservicesclub.constants.JobStatus;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.dtos.commons.FilterFieldDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;

@Slf4j
public class MongoUtil {

    private static Criteria getCriteriaForJobFilterRequest(JobFilterRequestDTO filter) {
	List<Criteria> criteriaList = new ArrayList<>();
	// default filters
	defaultFilters(filter, criteriaList);

	// date filter
	criteriaList.add(
		Criteria.where(POSTED_DATE.toString()).gte(LocalDateTime.now().minusDays(filter.getPostedXDaysAgo())));

	// dynamic field filters
	if (!CollectionUtils.isEmpty(filter.getFilterFields())) {
	    Map<String, List<FilterFieldDTO>> groupedFilterFields = filter.getFilterFields().stream()
		    .collect(Collectors.groupingBy(FilterFieldDTO::getGroupName));
	    groupedFilterFields.forEach((groupName, fields) -> {
		FieldTypeValue type = FieldTypeValue.valueOf(fields.get(0).getType());
		switch (type) {
		case NUMBER:
		    Optional<FilterFieldDTO> minField = fields.stream().filter(field -> {
			return field.getName().startsWith(NUMBER_TYPE_FIELD_MIN_PREFIX.toString());
		    }).findAny();
		    Optional<FilterFieldDTO> maxField = fields.stream().filter(field -> {
			return field.getName().startsWith(NUMBER_TYPE_FIELD_MAX_PREFIX.toString());
		    }).findAny();
		    if (minField.isPresent()) {
			criteriaList.add(Criteria.where(getJobDetailField(groupName))
				.gte(Integer.parseInt(minField.get().getValue())));
		    } else {
			log.warn("minField for field name {} not found in the filter", getJobDetailField(groupName));
		    }
		    if (maxField.isPresent()) {
			criteriaList.add(Criteria.where(getJobDetailField(groupName))
				.lte(Integer.parseInt(maxField.get().getValue())));
		    } else {
			log.warn("maxField for field name {} not found in the filter", getJobDetailField(groupName));
		    }
		    break;
		case CHECKBOX:
		    criteriaList.add(Criteria.where(getJobDetailField(groupName)).is(fields.get(0).getValue()));
		    break;
		default:
		    criteriaList.add(new Criteria(getJobDetailField(groupName))
			    .in(Collections.singletonList(fields.get(0).getValue())));
		    // throw new ServiceException(String.format("Invalid
		    // type {%s} of filter field
		    // passed!", type), 400);
		}
	    });
	}
	return new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
    }

    public static Criteria getFilterCriteria(JobFilterRequestDTO filterRequest) {
	Criteria filterCriteria;
	if (CollectionUtils.isEmpty(filterRequest.getJobIds())) {
	    filterCriteria = MongoUtil.getCriteriaForJobFilterRequest(filterRequest);
	} else {
	    List<Criteria> criteriaList = new ArrayList<>();
	    MongoUtil.defaultFilters(filterRequest, criteriaList);
	    criteriaList.add(Criteria.where(JobFieldNames.ID.toString()).in(filterRequest.getJobIds()));
	    filterCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
	}
	return filterCriteria;
    }

    public static void defaultFilters(JobFilterRequestDTO filter, List<Criteria> criteriaList) {
	criteriaList.add(Criteria.where(STATUS.toString()).is(JobStatus.AVAILABLE));
	criteriaList.add(Criteria.where(IS_DELETED.toString()).is(Boolean.FALSE));

	if (StringUtils.isNotEmpty(filter.getFilterTemplateName())) {
	    criteriaList.add(Criteria.where(FILTER_TEMPLATE_NAME.toString()).is(filter.getFilterTemplateName()));
	}
	if (filter.getServiceType() == null || filter.getServiceType() == ServiceTypeValue.ALL) {
	    criteriaList
		    .add(Criteria.where(SERVICE_TYPE.toString()).in(ServiceTypeValue.OFFER, ServiceTypeValue.REQUEST));
	} else {
	    criteriaList.add(Criteria.where(SERVICE_TYPE.toString()).is(filter.getServiceType()));
	}
    }

    private static String getJobDetailField(String groupName) {
	return JOB_DETAILS.toString() + "." + groupName;
    }

}
