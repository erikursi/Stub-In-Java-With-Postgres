package com.application.stub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capital {
    private String cityName;
    private String country;
    private int population;
}
