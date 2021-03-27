package sujalmandal.torncityservicesclub.utils;

import org.modelmapper.ModelMapper;

public class PojoUtils {
    private static ModelMapper mapper = new ModelMapper();

    public static ModelMapper getModelMapper(){
        return mapper;
    }
}
