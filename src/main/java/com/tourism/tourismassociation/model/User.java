package com.tourism.tourismassociation.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
// Refactoring code with Lombok, currently there is no option to exclude
// id from constructor, other than creating base class and inheritance

// check the way that setter for id should only apply on test methods
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false,name = "password_hash")
    private String passwordHash;

    @Column(nullable = false, name = "user_id")
    private String userId;

    //testing purposes
    public User( String email, String passwordHash){
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public User( String email, String passwordHash, String userId){
        this.email = email;
        this.passwordHash = passwordHash;
        this.userId=userId;
    }

}