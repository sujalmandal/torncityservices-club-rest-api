package sujalmandal.torncityservicesclub;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.services.JobService;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class MongoSequenceTest {

    @Autowired
    private JobService jobService;

    @Test
    void testSequenceGeneration() {

    }
}