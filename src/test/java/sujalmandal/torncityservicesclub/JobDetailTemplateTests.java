package sujalmandal.torncityservicesclub;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.models.jobdetails.HospitalizeJobDetails;
import sujalmandal.torncityservicesclub.utils.AppUtils;

@Slf4j
public class JobDetailTemplateTests {
    private HospitalizeJobDetails details;

    @BeforeEach
    public void setupTest() {
	details = new HospitalizeJobDetails();
	details.setPay(250_000);
	details.setTargetPlayerId("678232");
	details.setTotalHospitalizations(20);
    }

    @Test
    public void testJobDetailKeyNameFromImpl() {
	log.info(details.getJobDetailType());
	Assert.isTrue(details.getJobDetailType().equals(JobDetailTemplateValue.HOSPITALIZE.getFilterTemplateName()));
    }

    @Test
    public void testJobDetailToMap() {
	JobDetails superClassRef = details;
	superClassRef.toMap().forEach((k, v) -> {
	    log.info(k + "=" + v);
	});
	Assert.isTrue(!CollectionUtils.isEmpty(superClassRef.toMap()));
    }

    @Test
    public void testJobDetailFromMap() {
	AppUtils.loadJobDetailImplClasses();
	HashMap<String, Object> detailsAsMap = new HashMap<>();
	detailsAsMap.put("pay", 250_000);
	detailsAsMap.put("targetPlayerId", "678232");
	detailsAsMap.put("totalHospitalizations", 20);
	HospitalizeJobDetails generatedHospJobDetails = (HospitalizeJobDetails) JobDetails
		.fromMap(JobDetailTemplateValue.HOSPITALIZE.getFilterTemplateName(), detailsAsMap);
	log.info(generatedHospJobDetails.toString());
	Assert.isTrue(generatedHospJobDetails != null);
	Assert.isTrue(generatedHospJobDetails.equals(details));
    }
}
