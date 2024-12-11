package com.hacks.hackathon.asset;

import com.hacks.hackathon.city.City;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private AssetType assetType;
    private Long waterOnhand;
    private Long foodOnhand;
    private Long medicalOnhand;
    @ManyToOne
    private City location;
    private AssetType.AssetStatus status;
    private Long travelDaysRemaining;
}
