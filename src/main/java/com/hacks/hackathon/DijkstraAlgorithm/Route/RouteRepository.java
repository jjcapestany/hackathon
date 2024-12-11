package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    List<RouteEntity> findBySourceMap(AdjacencyMapEntity sourceMap);
}
