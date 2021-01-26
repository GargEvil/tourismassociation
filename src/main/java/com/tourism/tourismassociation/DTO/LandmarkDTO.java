package com.tourism.tourismassociation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandmarkDTO {

    private String name;

    private String description;

    private double geoLatitude;

    private double geoLongitude;

    private boolean active;

    private MunicipalityDTO municipality;

    private SignificanceDTO significance;

    public LandmarkDTO(String name, String description, double geoLatitude, double geoLongitude, boolean active) {
        this.name = name;
        this.description = description;
        this. geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
        this.active = active;
    }
}
