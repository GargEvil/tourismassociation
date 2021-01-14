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

    public User( String email, String passwordHash){

    }

    public User(long id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}