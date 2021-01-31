package com.tourism.tourismassociation.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class SignificanceDTO {

    @Range(min=1, max = 3, message = "Significance should be in range 1-3")
    private long id;
}
