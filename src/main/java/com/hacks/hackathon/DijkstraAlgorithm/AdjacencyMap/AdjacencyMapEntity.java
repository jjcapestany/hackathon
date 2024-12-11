package com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap;

import com.hacks.hackathon.city.City;
import com.hacks.hackathon.DijkstraAlgorithm.Route.RouteEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adjacency_maps")
@Getter
@Setter
@NoArgsConstructor
public class AdjacencyMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_city_id")
    private City originCity;

    @OneToMany(mappedBy = "sourceMap", cascade = CascadeType.ALL)
    private List<RouteEntity> routes = new ArrayList<>();
}