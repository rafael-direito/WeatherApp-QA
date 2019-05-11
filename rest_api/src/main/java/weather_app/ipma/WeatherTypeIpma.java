
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
    "descIdWeatherTypeEN",
    "descIdWeatherTypePT",
    "idWeatherType"
})
public class WeatherTypeIpma {

    @JsonProperty("descIdWeatherTypeEN")
    private String descIdWeatherTypeEN;
    @JsonProperty("descIdWeatherTypePT")
    private String descIdWeatherTypePT;
    @JsonProperty("idWeatherType")
    private Integer idWeatherType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("descIdWeatherTypeEN")
    public String getDescIdWeatherTypeEN() {
        return descIdWeatherTypeEN;
    }

    @JsonProperty("descIdWeatherTypeEN")
    public void setDescIdWeatherTypeEN(String descIdWeatherTypeEN) {
        this.descIdWeatherTypeEN = descIdWeatherTypeEN;
    }

    @JsonProperty("descIdWeatherTypePT")
    public String getDescIdWeatherTypePT() {
        return descIdWeatherTypePT;
    }

    @JsonProperty("descIdWeatherTypePT")
    public void setDescIdWeatherTypePT(String descIdWeatherTypePT) {
        this.descIdWeatherTypePT = descIdWeatherTypePT;
    }

    @JsonProperty("idWeatherType")
    public Integer getIdWeatherType() {
        return idWeatherType;
    }

    @JsonProperty("idWeatherType")
    public void setIdWeatherType(Integer idWeatherType) {
        this.idWeatherType = idWeatherType;
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
