package com.tourism.tourismassociation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {

    private Long landmarkId;

    private Long userId;

    private String comment;
}
