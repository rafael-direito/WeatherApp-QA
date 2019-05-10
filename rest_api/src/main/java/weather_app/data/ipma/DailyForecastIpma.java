
package weather_app.data.ipma;

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
    "precipitaProb",
    "tMin",
    "tMax",
    "predWindDir",
    "idWeatherType",
    "classWindSpeed",
    "longitude",
    "forecastDate",
    "classPrecInt",
    "latitude"
})
public class DailyForecastIpma {

    @JsonProperty("precipitaProb")
    private String precipitaProb;
    @JsonProperty("tMin")
    private String tMin;
    @JsonProperty("tMax")
    private String tMax;
    @JsonProperty("predWindDir")
    private String predWindDir;
    @JsonProperty("idWeatherType")
    private Integer idWeatherType;
    @JsonProperty("classWindSpeed")
    private Integer classWindSpeed;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("forecastDate")
    private String forecastDate;
    @JsonProperty("classPrecInt")
    private Integer classPrecInt;
    @JsonProperty("latitude")
    private String latitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("precipitaProb")
    public String getPrecipitaProb() {
        return precipitaProb;
    }

    @JsonProperty("precipitaProb")
    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    @JsonProperty("tMin")
    public String getTMin() {
        return tMin;
    }

    @JsonProperty("tMin")
    public void setTMin(String tMin) {
        this.tMin = tMin;
    }

    @JsonProperty("tMax")
    public String getTMax() {
        return tMax;
    }

    @JsonProperty("tMax")
    public void setTMax(String tMax) {
        this.tMax = tMax;
    }

    @JsonProperty("predWindDir")
    public String getPredWindDir() {
        return predWindDir;
    }

    @JsonProperty("predWindDir")
    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    @JsonProperty("idWeatherType")
    public Integer getIdWeatherType() {
        return idWeatherType;
    }

    @JsonProperty("idWeatherType")
    public void setIdWeatherType(Integer idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    @JsonProperty("classWindSpeed")
    public Integer getClassWindSpeed() {
        return classWindSpeed;
    }

    @JsonProperty("classWindSpeed")
    public void setClassWindSpeed(Integer classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("forecastDate")
    public String getForecastDate() {
        return forecastDate;
    }

    @JsonProperty("forecastDate")
    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    @JsonProperty("classPrecInt")
    public Integer getClassPrecInt() {
        return classPrecInt;
    }

    @JsonProperty("classPrecInt")
    public void setClassPrecInt(Integer classPrecInt) {
        this.classPrecInt = classPrecInt;
    }

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
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
    public String toString() {
        return "DailyForecast{" + 
                "precipitaProb=" + precipitaProb + 
                ", tMin=" + tMin + 
                ", tMax=" + tMax + 
                ", predWindDir=" + predWindDir + 
                ", idWeatherType=" + idWeatherType + 
                ", classWindSpeed=" + classWindSpeed + 
                ", longitude=" + longitude + 
                ", forecastDate=" + forecastDate + 
                ", classPrecInt=" + classPrecInt + 
                ", latitude=" + latitude + 
                ", additionalProperties=" + additionalProperties + '}';
    }
    
    

}
