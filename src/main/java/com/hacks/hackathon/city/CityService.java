package com.hacks.hackathon.city;

import com.hacks.hackathon.resources.ResourceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
