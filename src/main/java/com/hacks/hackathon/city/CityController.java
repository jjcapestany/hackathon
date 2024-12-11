package com.hacks.hackathon.city;

import com.hacks.hackathon.asset.Asset;
import com.hacks.hackathon.asset.AssetRepo;
import com.hacks.hackathon.asset.AssetService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/city")
public class CityController {
    private final AssetService assetService;
    private final CityRepo cityRepo;

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityRepo.findAll();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}/assets")
    public ResponseEntity<List<Asset>> getAssetsForCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(assetService.getAllAssetsByLocation(cityId));
    }
}
