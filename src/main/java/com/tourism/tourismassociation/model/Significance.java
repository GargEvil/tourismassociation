package com.tourism.tourismassociation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Significance {

    @Id
    private long id;

    private String grade;

    @OneToMany(mappedBy = "id",
    cascade = CascadeType.ALL)
    Set<Landmark> landmarkSet=
            new HashSet<Landmark>();


}
