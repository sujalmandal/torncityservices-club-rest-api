package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.enums.AppConstants.JOB_DETAILS;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX;
import static sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField.IS_DELETED;
import static sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField.POSTED_DATE;
import static sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField.SERVICE_TYPE;
import static sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField.STATUS;
import static sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField.FILTER_TEMPLATE_NAME;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.commons.FilterFieldDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Slf4j
public class MongoUtil {

    public static Criteria getCriteriaForJobFilterRequest(JobFilterRequestDTO filter) {
	List<Criteria> criteriaList = new ArrayList<>();
	// default filters
	criteriaList.add(Criteria.where(STATUS.toString()).is(JobStatus.AVAILABLE));
	criteriaList.add(Criteria.where(IS_DELETED.toString()).is(Boolean.FALSE));

	if (StringUtils.isNotEmpty(filter.getFilterTemplateName())) {
	    criteriaList.add(Criteria.where(FILTER_TEMPLATE_NAME.toString()).is(filter.getFilterTemplateName()));
	}
	if (filter.getServiceType() == null || filter.getServiceType() != ServiceTypeValue.ALL) {
	    criteriaList.add(Criteria.where(SERVICE_TYPE.toString()).is(filter.getServiceType()));
	} else {
	    criteriaList
		    .add(Criteria.where(SERVICE_TYPE.toString()).in(ServiceTypeValue.OFFER, ServiceTypeValue.REQUEST));
	}

	// date filter
	criteriaList.add(
		Criteria.where(POSTED_DATE.toString()).gte(LocalDateTime.now().minusDays(filter.getPostedXDaysAgo())));

	// dynamic field filters
	if (!CollectionUtils.isEmpty(filter.getFilterFields())) {
	    Map<String, List<FilterFieldDTO>> groupedFilterFields = filter.getFilterFields().stream()
		    .collect(Collectors.groupingBy(FilterFieldDTO::getGroupName));
	    groupedFilterFields.forEach((groupName, fields) -> {
		FormFieldTypeValue type = FormFieldTypeValue.valueOf(fields.get(0).getType());
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

    private static String getJobDetailField(String groupName) {
	return JOB_DETAILS.toString() + "." + groupName;
    }

    public static void paginateQuery(Query query, int pageNumber, int pageSize) {
	query.skip(pageNumber * pageSize);
	query.limit(pageSize);
    }

}
