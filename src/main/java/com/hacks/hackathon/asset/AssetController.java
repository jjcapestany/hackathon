package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import com.hacks.hackathon.resources.ResourceDto;
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

    @PutMapping("/{assetId}/task/{locationId}")
    public ResponseEntity<Asset> taskAsset(@PathVariable Long assetId,
                                           @PathVariable Long locationId,
                                           @RequestBody ResourceDto resourceDto) {

        Asset updatedAsset = assetService.taskAsset(assetId, locationId, resourceDto.resourceType(), resourceDto.quantity());
        if (updatedAsset != null) {
            return ResponseEntity.ok(updatedAsset);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
