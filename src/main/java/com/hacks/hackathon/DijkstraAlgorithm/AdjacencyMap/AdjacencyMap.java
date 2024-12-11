package com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap;

import com.hacks.hackathon.city.City;
import lombok.*;

import java.util.*;


@Getter
@Setter
public class AdjacencyMap {
    private Long id;
    private City originCity;
    private Integer cost = Integer.MAX_VALUE;
    private List<AdjacencyMap> shortestPath = new LinkedList<>();
    private Map<AdjacencyMap, Map<String, Integer>> neighborMap = new HashMap<>();

    public AdjacencyMap(City city) {
        this.originCity = city;
    }

    public void addDestination(AdjacencyMap destination, int distance, String mean) {
        Map<String, Integer> meanCost = neighborMap.computeIfAbsent(destination, k -> new HashMap<>());
        meanCost.put(mean, distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdjacencyMap that = (AdjacencyMap) o;
        return Objects.equals(originCity.getCityName(), that.originCity.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(originCity.getCityName());
    }

    @Override
    public String toString() {
        return originCity.getCityName();
    }
}