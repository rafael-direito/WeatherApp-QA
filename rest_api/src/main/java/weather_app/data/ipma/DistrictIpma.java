
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
    "idRegiao",
    "idAreaAviso",
    "idConcelho",
    "globalIdLocal",
    "latitude",
    "idDistrito",
    "local",
    "longitude"
})
public class DistrictIpma {

    @JsonProperty("idRegiao")
    private Integer idRegiao;
    @JsonProperty("idAreaAviso")
    private String idAreaAviso;
    @JsonProperty("idConcelho")
    private Integer idConcelho;
    @JsonProperty("globalIdLocal")
    private Integer globalIdLocal;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("idDistrito")
    private Integer idDistrito;
    @JsonProperty("local")
    private String local;
    @JsonProperty("longitude")
    private String longitude;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idRegiao")
    public Integer getIdRegiao() {
        return idRegiao;
    }

    @JsonProperty("idRegiao")
    public void setIdRegiao(Integer idRegiao) {
        this.idRegiao = idRegiao;
    }

    @JsonProperty("idAreaAviso")
    public String getIdAreaAviso() {
        return idAreaAviso;
    }

    @JsonProperty("idAreaAviso")
    public void setIdAreaAviso(String idAreaAviso) {
        this.idAreaAviso = idAreaAviso;
    }

    @JsonProperty("idConcelho")
    public Integer getIdConcelho() {
        return idConcelho;
    }

    @JsonProperty("idConcelho")
    public void setIdConcelho(Integer idConcelho) {
        this.idConcelho = idConcelho;
    }

    @JsonProperty("globalIdLocal")
    public Integer getGlobalIdLocal() {
        return globalIdLocal;
    }

    @JsonProperty("globalIdLocal")
    public void setGlobalIdLocal(Integer globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("idDistrito")
    public Integer getIdDistrito() {
        return idDistrito;
    }

    @JsonProperty("idDistrito")
    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    @JsonProperty("local")
    public String getLocal() {
        return local;
    }

    @JsonProperty("local")
    public void setLocal(String local) {
        this.local = local;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
