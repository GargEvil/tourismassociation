package com.tourism.tourismassociation.model;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    private long id;


    private String email;


    private String passwordHash;

    public User() {}

    public User(long id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }


}