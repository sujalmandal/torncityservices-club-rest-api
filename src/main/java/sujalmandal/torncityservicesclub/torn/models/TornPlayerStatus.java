
package sujalmandal.torncityservicesclub.torn.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "description", "details", "state", "color", "until" })
@Data
public class TornPlayerStatus {

    @JsonProperty("description")
    public String description;
    @JsonProperty("details")
    public String details;
    @JsonProperty("state")
    public String state;
    @JsonProperty("color")
    public String color;
    @JsonProperty("until")
    public Integer until;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}