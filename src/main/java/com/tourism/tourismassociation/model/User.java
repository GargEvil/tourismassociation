package com.tourism.tourismassociation.model;


import lombok.*;

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


    private String email;

    @Column(name="password_hash")
    private String passwordHash;


    public User( String email, String passwordHash){
        this.email = email;
        this.passwordHash = passwordHash;
    }

}