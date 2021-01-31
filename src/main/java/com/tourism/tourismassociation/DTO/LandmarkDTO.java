package com.tourism.tourismassociation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LandmarkDTO {

    @NotBlank
    private String name;

    private String description;

    @Positive
    private double geoLatitude;

    @Positive
    private double geoLongitude;

    private boolean active;

    @Valid
    private MunicipalityDTO municipality;

    @Valid
    private SignificanceDTO significance;

    public LandmarkDTO(String name, String description, double geoLatitude, double geoLongitude, boolean active) {
        this.name = name;
        this.description = description;
        this. geoLatitude = geoLatitude;
        this.geoLongitude = geoLongitude;
        this.active = active;
    }
}
