package com.afkl.cases.pa.model;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"locations"
})
public class Locations {

@JsonProperty("locations")
private List<Location> locations = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("locations")
public List<Location> getLocations() {
return locations;
}

@JsonProperty("locations")
public void setLocations(List<Location> locations) {
this.locations = locations;
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