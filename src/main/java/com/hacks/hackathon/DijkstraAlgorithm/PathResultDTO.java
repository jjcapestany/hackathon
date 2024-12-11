package com.hacks.hackathon.DijkstraAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathResultDTO {
    private boolean pathFound;
    private String transportationType;
    private int totalDistance = Integer.MAX_VALUE;
    private List<String> cityNames;
    private List<RouteDTO> routes;
}