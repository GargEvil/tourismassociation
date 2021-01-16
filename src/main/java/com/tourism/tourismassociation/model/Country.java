package com.tourism.tourismassociation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Country {

    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "id",
    cascade = CascadeType.ALL)
    Set<Municipality> municipalitySet =
            new HashSet<Municipality>();

}
