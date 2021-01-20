package com.tourism.tourismassociation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //Constructors made for testing purposes
    public Landmark(int id, String name, double geoLatitude, double geoLongitude, boolean active) {
        this.id = id;
        this.name = name;
        this.geoLatitude=geoLatitude;
        this.geoLongitude = geoLongitude;
        this.active = active;
    }

    public Landmark(String name, double geoLatitude, double geoLongitude, boolean active) {
        this.name = name;
        this.geoLatitude=geoLatitude;
        this.geoLongitude = geoLongitude;
        this.active = active;
    }
}
