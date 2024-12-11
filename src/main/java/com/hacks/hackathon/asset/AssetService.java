package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetService {
    AssetRepo assetRepo;

    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    public List<Asset> getAllAssetsByLocation(Long locationId) {
        return assetRepo.findAllByLocationId(locationId);
    }

    public Asset getAssetById(Long assetId) {
        return assetRepo.findById(assetId).orElse(null);
    }

}
