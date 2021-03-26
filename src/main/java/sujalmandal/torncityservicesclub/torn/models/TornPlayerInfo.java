package sujalmandal.torncityservicesclub.torn.models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "level", "gender", "player_id", "name", "status" })
@Data
public class TornPlayerInfo {

    @JsonProperty("level")
    public Integer level;
    @JsonProperty("gender")
    public String gender;
    @JsonProperty("player_id")
    public Integer playerId;
    @JsonProperty("name")
    public String name;
    @JsonProperty("status")
    public TornPlayerStatus status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}