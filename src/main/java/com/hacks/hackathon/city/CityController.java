package com.hacks.hackathon.city;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/city")
public class CityController {
    private final CityRepo cityRepo;

    @GetMapping
    List<City> getsCityData() {
        return cityRepo.findAll();
    }
}
