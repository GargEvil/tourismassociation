package com.tourism.tourismassociation.DTO;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MunicipalityDTO {

    @NotNull
    @Range(min = 1, max = 5) //Currently I have only 5 municipalities in db
    private long id;
}
