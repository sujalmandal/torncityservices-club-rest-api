package sujalmandal.torncityservicesclub.utils;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.models.Job;

public class PojoUtils {
    private static ModelMapper modelMapper;
    private static ObjectMapper objectMapper;

    static {
	modelMapper = new ModelMapper();
	objectMapper = new ObjectMapper();
	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static ModelMapper getModelMapper() {
	return modelMapper;
    }

    public static ObjectMapper getObjectMapper() {
	return objectMapper;
    }

    public static Job getJobFromDTO(CreateJobRequestDTO request) {
	Job job = new Job();
	PojoUtils.getModelMapper().map(request, job);
	return job;
    }
}
