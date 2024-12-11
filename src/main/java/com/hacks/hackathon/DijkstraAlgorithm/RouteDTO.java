package com.hacks.hackathon.DijkstraAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private Long id;
    private String sourceCityName;
    private String destinationCityName;
    private String transportationType;
    private Integer cost;
}

