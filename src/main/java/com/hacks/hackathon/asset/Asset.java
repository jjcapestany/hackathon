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
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private City location;
    private Long waterCapacity;
    private Long foodCapacity;
    private Long aidCapacity;
    private Double fuelUsageRate;
    private Long speed;
}
