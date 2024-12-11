package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetType {

    public enum AssetTravelType {
        AIR,
        GROUND,
        SEA
    }

    public enum AssetStatus {
        STANDBY,
        ENR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private AssetTravelType modeOfTravel;
    private Long waterCapacity;
    private Long foodCapacity;
    private Long aidCapacity;
    private Double fuelUsageRate;
    private Long speed;
}
