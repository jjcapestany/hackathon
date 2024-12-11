package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface AssetRepo extends JpaRepository<Asset, Long> {
    List<Asset> findAllByLocationId(Long locationId);
}
