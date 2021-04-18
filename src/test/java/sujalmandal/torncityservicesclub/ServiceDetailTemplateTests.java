package sujalmandal.torncityservicesclub;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;
import sujalmandal.torncityservicesclub.models.service.offers.HospitalizeServiceOffer;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

@Slf4j
public class ServiceDetailTemplateTests {
    private HospitalizeServiceOffer details;

    @BeforeEach
    public void setupTest() {
	details = new HospitalizeServiceOffer();
	details.setPay(250_000L);
	details.setTotalHospitalizations(20);
    }

    @Test
    public void testGetTemplateNameFromJobDetailImpl() {
	log.info(details.getTemplateMetadata().getTemplateName());
	Assert.isTrue(details.getTemplateMetadata().getTemplateName()
		.equals(TemplateValue.HOSPITALIZE_OFFER.getTemplateName()));
    }

    @Test
    public void testJobDetailToMap() {
	ServiceDetail superClassRef = details;
	superClassRef.toMap().forEach((k, v) -> {
	    log.info(k + "=" + v);
	});
	Assert.isTrue(!CollectionUtils.isEmpty(superClassRef.toMap()));
    }

    @Test
    public void testJobDetailFromMap() {
	TemplateUtil.loadTemplatizedClasses();
	HashMap<String, Object> detailsAsMap = new HashMap<>();
	detailsAsMap.put("pay", 250_000);
	detailsAsMap.put("targetPlayerId", "678232");
	detailsAsMap.put("totalHospitalizations", 20);
	HospitalizeServiceOffer generatedHospJobDetails = (HospitalizeServiceOffer) ServiceDetail
		.fromMap(TemplateValue.HOSPITALIZE_OFFER.getTemplateName(), detailsAsMap);
	log.info(generatedHospJobDetails.toString());
	Assert.isTrue(generatedHospJobDetails != null);
	Assert.isTrue(generatedHospJobDetails.equals(details));
    }
}
