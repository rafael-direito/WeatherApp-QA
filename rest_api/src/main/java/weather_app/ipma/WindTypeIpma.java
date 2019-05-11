
package x;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "descClassWindSpeedDailyEN",
    "descClassWindSpeedDailyPT",
    "classWindSpeed"
})
public class WindTypeIpma {

    @JsonProperty("descClassWindSpeedDailyEN")
    private String descClassWindSpeedDailyEN;
    @JsonProperty("descClassWindSpeedDailyPT")
    private String descClassWindSpeedDailyPT;
    @JsonProperty("classWindSpeed")
    private String classWindSpeed;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("descClassWindSpeedDailyEN")
    public String getDescClassWindSpeedDailyEN() {
        return descClassWindSpeedDailyEN;
    }

    @JsonProperty("descClassWindSpeedDailyEN")
    public void setDescClassWindSpeedDailyEN(String descClassWindSpeedDailyEN) {
        this.descClassWindSpeedDailyEN = descClassWindSpeedDailyEN;
    }

    @JsonProperty("descClassWindSpeedDailyPT")
    public String getDescClassWindSpeedDailyPT() {
        return descClassWindSpeedDailyPT;
    }

    @JsonProperty("descClassWindSpeedDailyPT")
    public void setDescClassWindSpeedDailyPT(String descClassWindSpeedDailyPT) {
        this.descClassWindSpeedDailyPT = descClassWindSpeedDailyPT;
    }

    @JsonProperty("classWindSpeed")
    public String getClassWindSpeed() {
        return classWindSpeed;
    }

    @JsonProperty("classWindSpeed")
    public void setClassWindSpeed(String classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
