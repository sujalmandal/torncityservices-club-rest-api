package sujalmandal.torncityservicesclub.dtos.commons;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.MoneyFieldType;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;

@Data
public class TemplateMetaInfo {

    private String templateName;
    private String formLabel;
    private String filterLabel;
    private ServiceTypeValue serviceType;
    private MoneyFieldType moneyFieldType;

}