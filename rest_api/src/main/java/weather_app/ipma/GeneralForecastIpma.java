
package weather_app.ipma;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "owner",
    "country",
    "data",
    "globalIdLocal",
    "dataUpdate"
})
public class GeneralForecastIpma {
    
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("country")
    private String country;
    @JsonProperty("data")
    private List<DailyForecastIpma> data = null;
    @JsonProperty("globalIdLocal")
    private Integer globalIdLocal;
    @JsonProperty("dataUpdate")
    private String dataUpdate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }
    
    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    @JsonProperty("country")
    public String getCountry() {
        return country;
    }
    
    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }
    
    @JsonProperty("data")
    public List<DailyForecastIpma> getData() {
        return data;
    }
    
    @JsonProperty("data")
    public void setData(List<DailyForecastIpma> data) {
        this.data = data;
    }
    
    @JsonProperty("globalIdLocal")
    public Integer getGlobalIdLocal() {
        return globalIdLocal;
    }
    
    @JsonProperty("globalIdLocal")
    public void setGlobalIdLocal(Integer globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }
    
    @JsonProperty("dataUpdate")
    public String getDataUpdate() {
        return dataUpdate;
    }
    
    @JsonProperty("dataUpdate")
    public void setDataUpdate(String dataUpdate) {
        this.dataUpdate = dataUpdate;
    }
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @Override
    public String toString() 
    {
        return "GeneralForecast{" + 
                "owner=" + owner +
                ", country=" + country + 
                ", data=" + data + 
                ", globalIdLocal=" + globalIdLocal + 
                ", dataUpdate=" + dataUpdate + 
                ", additionalProperties=" + additionalProperties + '}';
    }
    
    
}
