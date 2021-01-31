package com.tourism.tourismassociation.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@Setter
public class RatingDTO {

    @Min(1)
    @Max(5)
    private int grade;

    private Long landmarkId;

    private Long userId;

    private String comment;
}
