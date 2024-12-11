package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import com.hacks.hackathon.city.CityService;
import com.hacks.hackathon.resources.ResourceType;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AssetService {
    AssetRepo assetRepo;
    CityService cityService;

    public final int TICK_RATE = 2000;

    public List<Asset> getAllAssets() {
        return assetRepo.findAll();
    }

    @Scheduled(fixedDelay = TICK_RATE)
    @Transactional
    public void distributeAssets() {
        System.out.println("WE ARE DISTRIBUTING");
        List<Asset> assets = getAllAssets();
        for (Asset asset : assets) {
            checkIfAssetLanded(asset);
        }
    }

    public void checkIfAssetLanded(Asset asset) {
        Long timeRemaining = asset.getTravelDaysRemaining();
        if (asset.getTravelDaysRemaining() > 0) {
            asset.setTravelDaysRemaining(timeRemaining - 1);
        } else if (timeRemaining == 0 && asset.getStatus() == AssetType.AssetStatus.ENR) {
            System.out.println("ASSET LANDED");
            cityService.modifyCityResources(asset.getLocation().getId(), ResourceType.MEDICAL, asset.getMedicalOnhand());
            cityService.modifyCityResources(asset.getLocation().getId(), ResourceType.FOOD, asset.getFoodOnhand());
            cityService.modifyCityResources(asset.getLocation().getId(), ResourceType.WATER, asset.getWaterOnhand());
            asset.setFoodOnhand(0L);
            asset.setWaterOnhand(0L);
            asset.setMedicalOnhand(0L);
            asset.setStatus(AssetType.AssetStatus.STANDBY);
        }
    }

    public List<Asset> getAllAssetsByLocation(Long locationId) {
        return assetRepo.findAllByLocationId(locationId);
    }

    public Asset getAssetById(Long assetId) {
        return assetRepo.findById(assetId).orElse(null);
    }

    @Transactional
    public Asset taskAsset(Long assetId, Long locationId, ResourceType resourceType, Long resourceQty) {
        Asset result = null;
        Asset asset = getAssetById(assetId);
        City city = cityService.getCityById(locationId);
        if (asset != null && city != null
                && asset.getStatus() == AssetType.AssetStatus.STANDBY
                && cityService.modifyCityResources(city.getId(), resourceType, -resourceQty)) {
            switch (resourceType) {
                case FOOD -> asset.setFoodOnhand(resourceQty);
                case WATER -> asset.setWaterOnhand(resourceQty);
                case MEDICAL -> asset.setMedicalOnhand(resourceQty);
            }
            asset.setLocation(city);
            asset.setStatus(AssetType.AssetStatus.ENR);
            //TODO: THIS SHOULD BE CALCULATED
            asset.setTravelDaysRemaining(5L);
            result = assetRepo.saveAndFlush(asset);
        }
        return result;
    }
}
