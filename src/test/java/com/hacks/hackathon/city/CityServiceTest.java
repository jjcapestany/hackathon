package com.hacks.hackathon.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceTest {
    @Autowired
    CityService cityService;
    @Autowired
    CityRepo cityRepo;

    @Test
    void getsAllCities() {
        List<City> mockCities = List.of(City.builder().name("new").id(1L).population(2).build());
        cityRepo.saveAll(mockCities);
        List<City> cities = cityService.getAllCities();
        assertEquals(mockCities.size(), cities.size());
    }

}