package com.tourism.tourismassociation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Landmark {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    private byte[] pictures;

    private double geoLatitude;

    private double geoLongitude;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private Municipality municipalityId;

    private boolean active;

    private float avgRating;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private Significance significanceId;

    @OneToMany(fetch =FetchType.LAZY,mappedBy = "id")
    private List<Rating> ratings;

}
