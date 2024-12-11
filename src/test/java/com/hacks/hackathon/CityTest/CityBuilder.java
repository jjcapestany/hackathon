package com.hacks.hackathon.CityTest;

import com.hacks.hackathon.city.City;

public class CityBuilder {
    public static City buildCity() {
        return buildCity("Austin");
    }

    public static City buildCity(String name) {
        return City.builder()
                .id(1L)
                .cityName(name)
                .population(1000)
                .xAxis(100)
                .yAxis(100)
                .build();
    }
    public static City buildCity(Long id, String name) {
        return City.builder()
                .id(id)
                .cityName(name)
                .population(1000)
                .xAxis(100)
                .yAxis(100)
                .build();
    }
}
