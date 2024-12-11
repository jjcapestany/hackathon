package com.hacks.hackathon.city;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
        CityRepo cityRepo;

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }
}
