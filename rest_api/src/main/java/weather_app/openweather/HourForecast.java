/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.openweather;

/**
 *
 * @author rd
 */
public class HourForecast 
{
    private String weatherType;
    private Double tMax;
    private Double tMin;
    private String windDir; 
    private Double precipProb;
    private Double humidity;
    private Double pressure;
    private Double longitude;
    private Double latitude;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherType() {
        return weatherType;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public Double gettMax() {
        return tMax;
    }

    public void settMax(Double tMax) {
        this.tMax = tMax;
    }

    public Double gettMin() {
        return tMin;
    }

    public void settMin(Double tMin) {
        this.tMin = tMin;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Double getPrecipProb() {
        return precipProb;
    }

    public void setPrecipProb(Double precipProb) {
        this.precipProb = precipProb;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    
    
    
}
