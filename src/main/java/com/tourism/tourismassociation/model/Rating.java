package com.tourism.tourismassociation.model;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private long id;

    private String comment;

    @ManyToOne
    @JoinColumn(columnDefinition = "landmark_id")
    private Landmark landmark;

    @ManyToOne
    @JoinColumn(columnDefinition = "user_id")
    private User user;


}
