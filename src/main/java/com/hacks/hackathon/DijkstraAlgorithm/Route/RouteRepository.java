package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapEntity;
import com.hacks.hackathon.city.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    @Query(value = "SELECT r FROM RouteEntity r WHERE r.sourceMap.originCity.name = :sourceCityName " +
            "AND r.destinationMap.originCity.name = :destCityName " +
            "AND r.transportationType = :transportationType")
    Optional<RouteEntity> findRouteEntitiesBySourceNameDestinationNameAndTransportationType(
            @Param("sourceCityName") String sourceCityName,
            @Param("destCityName") String destCityName,
            @Param("transportationType") String transportationType);
}