package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/all")
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long assetId) {
        return ResponseEntity.ok(assetService.getAssetById(assetId));
    }

}
