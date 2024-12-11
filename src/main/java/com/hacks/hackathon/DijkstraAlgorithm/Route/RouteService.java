package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMap;
import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapEntity;
import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapRepository;
import com.hacks.hackathon.DijkstraAlgorithm.*;
import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {
    private final AdjacencyMapRepository adjacencyMapRepository;
    private final RouteRepository routeRepository;
    private final CityRepo cityRepository;

    public PathResultDTO findShortestPath(Long sourceCityId, Long destCityId, String transportationType) {
        City sourceCity = cityRepository.findById(sourceCityId)
                .orElseThrow(() -> new EntityNotFoundException("Source city not found"));
        City destCity = cityRepository.findById(destCityId)
                .orElseThrow(() -> new EntityNotFoundException("Destination city not found"));

        Graph graph = buildGraphFromDatabase(transportationType);
        AdjacencyMap sourceMap = findOrCreateAdjacencyMap(graph, sourceCity);

        Graph.PathResult result = Graph.calculateShortestPathFromSourceToDestination(
                graph, sourceMap, destCity, transportationType);

        return convertToDTO(result);
    }

    private AdjacencyMap findOrCreateAdjacencyMap(Graph graph, City city) {
        return graph.getAdjacencyMaps().stream()
                .filter(map -> map.getOriginCity().equals(city))
                .findFirst()
                .orElseGet(() -> {
                    AdjacencyMap newMap = new AdjacencyMap(city);
                    graph.addAdjacencyMap(newMap);
                    return newMap;
                });
    }

    private Graph buildGraphFromDatabase(String transportationType) {
        Graph graph = new Graph();
        Map<City, AdjacencyMap> cityToMapMapping = new HashMap<>();

        List<AdjacencyMapEntity> allMaps = adjacencyMapRepository.findAll();

        for (AdjacencyMapEntity entity : allMaps) {
            AdjacencyMap map = new AdjacencyMap(entity.getOriginCity());
            cityToMapMapping.put(entity.getOriginCity(), map);
            graph.addAdjacencyMap(map);
        }

        for (AdjacencyMapEntity entity : allMaps) {
            AdjacencyMap sourceMap = cityToMapMapping.get(entity.getOriginCity());
            for (RouteEntity route : entity.getRoutes()) {
                AdjacencyMap destMap = cityToMapMapping.get(route.getDestinationMap().getOriginCity());
                if (destMap != null) {  // Safety check
                    sourceMap.addDestination(destMap, route.getCost(), route.getTransportationType());
                }
            }
        }

        return graph;
    }

    private PathResultDTO convertToDTO(Graph.PathResult result) {
        if (!result.isPathFound()) {
            return new PathResultDTO(false, result.getTransportationType(), 0, new ArrayList<>(), new ArrayList<>());
        }

        List<String> cityNames = result.getPath().stream()
                .map(City::getCityName)
                .collect(Collectors.toList());

        List<RouteDTO> routes = new ArrayList<>();
        for (int i = 0; i < result.getPath().size() - 1; i++) {
            routes.add(new RouteDTO(
                    null,
                    result.getPath().get(i).getCityName(),
                    result.getPath().get(i + 1).getCityName(),
                    result.getTransportationType(),
                    null // Cost will be filled from database
            ));
        }

        return new PathResultDTO(
                result.isPathFound(),
                result.getTransportationType(),
                result.getTotalDistance(),
                cityNames,
                routes
        );
    }
    public RouteDTO createRoute(RouteDTO routeDTO) {
        City sourceCity = cityRepository.findByCityName(routeDTO.getSourceCityName())
                .orElseThrow(() -> new EntityNotFoundException("Source city not found: " + routeDTO.getSourceCityName()));

        City destCity = cityRepository.findByCityName(routeDTO.getDestinationCityName())
                .orElseThrow(() -> new EntityNotFoundException("Destination city not found: " + routeDTO.getDestinationCityName()));

        AdjacencyMapEntity sourceMap = adjacencyMapRepository.findByOriginCity(sourceCity)
                .orElseGet(() -> {
                    AdjacencyMapEntity newMap = new AdjacencyMapEntity();
                    newMap.setOriginCity(sourceCity);
                    return adjacencyMapRepository.save(newMap);
                });

        AdjacencyMapEntity destMap = adjacencyMapRepository.findByOriginCity(destCity)
                .orElseGet(() -> {
                    AdjacencyMapEntity newMap = new AdjacencyMapEntity();
                    newMap.setOriginCity(destCity);
                    return adjacencyMapRepository.save(newMap);
                });

        RouteEntity route = new RouteEntity();
        route.setSourceMap(sourceMap);
        route.setDestinationMap(destMap);
        route.setTransportationType(routeDTO.getTransportationType());
        route.setCost(routeDTO.getCost());

        RouteEntity savedRoute = routeRepository.save(route);

        return new RouteDTO(
                savedRoute.getId(),
                sourceCity.getCityName(),
                destCity.getCityName(),
                savedRoute.getTransportationType(),
                savedRoute.getCost()
        );
    }
}