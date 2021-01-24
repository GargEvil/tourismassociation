package com.tourism.tourismassociation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LandmarkDTO {
// Try setting municipality and significance as long, then in LandmarkService use BeanUtils > ModelMapper

    private String name;

    private String description;

    private double geoLatitude;

    private double geoLongitude;

    private boolean active;

    private MunicipalityDTO municipality;

    private SignificanceDTO significance;
}
