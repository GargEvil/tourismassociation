package com.tourism.tourismassociation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private byte[] pictures;

    @Column(name= "geo_latitude")
    private double geoLatitude;

    @Column(name= "geo_longitude")
    private double geoLongitude;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    private boolean active;

    @Transient
    private float avgRating;

    @ManyToOne
    @JoinColumn(name = "significance_id")
    private Significance significance;


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
