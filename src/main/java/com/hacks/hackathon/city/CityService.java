package com.hacks.hackathon.city;

import com.hacks.hackathon.asset.Asset;
import com.hacks.hackathon.resources.ResourceType;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    CityRepo cityRepo;

    public List<City> getAllCities() {
        return cityRepo.findAll();
    }

    public City getCityById(Long id) {
        return cityRepo.findById(id).orElse(null);
    }

    public final int TICK_RATE_MS = 10000;

    @Scheduled(fixedDelay = TICK_RATE_MS)
    @Transactional
    public void consumeAid() {
        System.out.println("AID HAS BEEN CONSUMED");
        List<City> cities = getAllCities();

        for (City city : cities) {
            consumeResource(city, ResourceType.WATER, city.getWater().getUsageRate());
            consumeResource(city, ResourceType.FOOD, city.getFood().getUsageRate());
            consumeResource(city, ResourceType.MEDICAL, city.getMedical().getUsageRate());
        }
    }

    private void consumeResource(City city, ResourceType resourceType, double usageRate) {
        Long currentOnHand = getCurrentResourceOnHand(city, resourceType);
        Long amountConsumed = Math.negateExact( (long) usageRate);

        modifyCityResources(city.getId(), resourceType, amountConsumed);

        if (currentOnHand-amountConsumed <= 0 && !city.getName().equals("TF Main")) {
            System.out.printf("Warning: %s in city %s has been depleted.%n", resourceType, city.getName());
        }
    }

    private Long getCurrentResourceOnHand(City city, ResourceType resourceType) {
        return switch (resourceType) {
            case WATER -> city.getWater().getOnHand();
            case FOOD -> city.getFood().getOnHand();
            case MEDICAL -> city.getMedical().getOnHand();
            default -> throw new IllegalArgumentException("Unknown resource type: " + resourceType);
        };
    }

    public boolean modifyCityResources(Long cityId, ResourceType resource, Long resourceQuantity) {
        boolean result = false;
        City city = getCityById(cityId);
        if (city != null) {
            Long currentResourceAmt;
            Long updatedResourceAmt;
            switch (resource) {
                case FUEL -> {
                    currentResourceAmt = city.getFuel();
                    updatedResourceAmt = currentResourceAmt + resourceQuantity;
                    if (updatedResourceAmt > 0) {
                        city.setFuel(currentResourceAmt + resourceQuantity);
                        result = true;
                    }
                }
                case WATER -> {
                    currentResourceAmt = city.getWater().getOnHand();
                    updatedResourceAmt = Math.min(
                            currentResourceAmt + resourceQuantity,
                            city.getWater().getCapacity());
                    if (updatedResourceAmt > 0) {
                        city.getWater().setOnHand(updatedResourceAmt);
                        result = true;
                    }
                }
                case FOOD -> {
                    currentResourceAmt = city.getFood().getOnHand();
                    updatedResourceAmt = Math.min(
                            currentResourceAmt + resourceQuantity,
                            city.getFood().getCapacity());
                    if (updatedResourceAmt > 0) {
                        city.getFood().setOnHand(updatedResourceAmt);
                        result = true;
                    }
                }
                case MEDICAL -> {
                    currentResourceAmt = city.getMedical().getOnHand();
                    updatedResourceAmt = Math.min(
                            currentResourceAmt + resourceQuantity,
                            city.getMedical().getCapacity());
                    if (updatedResourceAmt > 0) {
                        city.getMedical().setOnHand(updatedResourceAmt);
                        result = true;
                    }
                }
            }
        }
        return result;
    }
}
