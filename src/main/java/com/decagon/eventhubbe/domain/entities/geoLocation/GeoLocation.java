package com.decagon.eventhubbe.domain.entities.geoLocation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation {
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
}
