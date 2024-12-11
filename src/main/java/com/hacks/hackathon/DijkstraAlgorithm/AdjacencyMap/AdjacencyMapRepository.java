package com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap;

import com.hacks.hackathon.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdjacencyMapRepository extends JpaRepository<AdjacencyMapEntity, Long> {
    Optional<AdjacencyMapEntity> findByOriginCity(City city);
}