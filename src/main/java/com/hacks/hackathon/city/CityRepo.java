package com.hacks.hackathon.city;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CityRepo extends JpaRepository<City, Long> {
}
