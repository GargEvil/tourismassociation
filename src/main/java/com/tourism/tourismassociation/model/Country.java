package com.tourism.tourismassociation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Country {

    @Id
    private long id;

    private String name;

}
