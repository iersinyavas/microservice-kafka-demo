package com.microservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Age {
    private int male;
    private int female;
    private int deadMale;
    private int deadFemale;
    private int maleHealth;
    private int femaleHealth;
}
