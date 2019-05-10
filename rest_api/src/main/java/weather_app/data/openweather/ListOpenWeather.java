
package weather_app.data.openweather;

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
    "dt",
    "main",
    "weather",
    "clouds",
    "wind",
    "sys",
    "dt_txt"
})
public class ListOpenWeather {

    @JsonProperty("dt")
    private Integer dt;
    @JsonProperty("main")
    private MainOpenWeather main;
    @JsonProperty("weather")
    private java.util.List<WeatherOpenWeather> weather = null;
    @JsonProperty("clouds")
    private CloudsOpenWeather clouds;
    @JsonProperty("wind")
    private WindOpenWeather wind;
    @JsonProperty("sys")
    private SysOpenWeather sys;
    @JsonProperty("dt_txt")
    private String dtTxt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("dt")
    public Integer getDt() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDt(Integer dt) {
        this.dt = dt;
    }

    @JsonProperty("main")
    public MainOpenWeather getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(MainOpenWeather main) {
        this.main = main;
    }

    @JsonProperty("weather")
    public java.util.List<WeatherOpenWeather> getWeather() {
        return weather;
    }

    @JsonProperty("weather")
    public void setWeather(java.util.List<WeatherOpenWeather> weather) {
        this.weather = weather;
    }

    @JsonProperty("clouds")
    public CloudsOpenWeather getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(CloudsOpenWeather clouds) {
        this.clouds = clouds;
    }

    @JsonProperty("wind")
    public WindOpenWeather getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(WindOpenWeather wind) {
        this.wind = wind;
    }

    @JsonProperty("sys")
    public SysOpenWeather getSys() {
        return sys;
    }

    @JsonProperty("sys")
    public void setSys(SysOpenWeather sys) {
        this.sys = sys;
    }

    @JsonProperty("dt_txt")
    public String getDtTxt() {
        return dtTxt;
    }

    @JsonProperty("dt_txt")
    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
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
