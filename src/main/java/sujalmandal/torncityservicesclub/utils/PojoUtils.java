package sujalmandal.torncityservicesclub.utils;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PojoUtils {
    private static ModelMapper modelMapper;
    private static ObjectMapper objectMapper;

    static {
	modelMapper = new ModelMapper();
	objectMapper = new ObjectMapper();
	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private static ModelMapper getModelMapper() {
	return modelMapper;
    }

    public static <S, D> D map(S source, D destination) {
	getModelMapper().map(source, destination);
	return destination;
    }

    public static ObjectMapper getObjectMapper() {
	return objectMapper;
    }

}