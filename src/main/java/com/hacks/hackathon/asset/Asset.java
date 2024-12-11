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
public class Asset extends AssetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long waterOnhand;
    private Long foodOnhand;
    private Long medicalOnhand;
    @ManyToOne
    private City location;
    private AssetStatus status;
    private Long travelDaysRemaining;
}
