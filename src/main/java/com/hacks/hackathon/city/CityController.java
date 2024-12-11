package com.hacks.hackathon.city;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/city")
public class CityController {
    private final CityRepo cityRepo;

    @GetMapping
    public ResponseEntity<List<City>> getallCitiesById() {
        List<City> cities = cityRepo.findAll();
        return ResponseEntity.ok(cities);
    }
}
