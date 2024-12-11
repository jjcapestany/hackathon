package com.hacks.hackathon.CityTest;

import com.hacks.hackathon.city.City;

public class CityBuilder {
    public static City buildCity() {
        return buildCity("Austin");
    }

    public static City buildCity(String name) {
        return City.builder()
                .id(1L)
                .name(name)
                .population(1000)
                .xCoord(100)
                .yCoord(100)
                .build();
    }
    public static City buildCity(Long id, String name) {
        return City.builder()
                .id(id)
                .name(name)
                .population(1000)
                .xCoord(100)
                .yCoord(100)
                .build();
    }
}
