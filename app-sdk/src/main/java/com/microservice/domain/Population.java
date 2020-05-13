package com.microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Population {

    private Long population;
    private Integer birth;
    private Integer marriage;
    private Integer currentMale;
    private Integer currentFemale;
    private Age age;
    private Integer year;

}
