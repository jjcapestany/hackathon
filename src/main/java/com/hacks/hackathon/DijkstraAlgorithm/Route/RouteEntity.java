package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RouteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_map_id")
    private AdjacencyMapEntity sourceMap;

    @ManyToOne
    @JoinColumn(name = "destination_map_id")
    private AdjacencyMapEntity destinationMap;

    private String transportationType;
    private Integer cost;
}