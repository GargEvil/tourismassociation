package com.tourism.tourismassociation.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Municipality {

    @Id
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(columnDefinition = "id")
    private Country countryId;

    @OneToMany(mappedBy = "id",
    cascade = CascadeType.ALL)
    Set<Landmark> landmarkSet = new HashSet<Landmark>();

}
