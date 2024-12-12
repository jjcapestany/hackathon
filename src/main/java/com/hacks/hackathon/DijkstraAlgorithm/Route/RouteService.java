package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMap;
import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapEntity;
import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapRepository;
import com.hacks.hackathon.DijkstraAlgorithm.*;
import com.hacks.hackathon.city.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
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

        // More detailed debugging
        System.out.println("Source city details - ID: " + sourceCity.getId() +
                ", Name: " + sourceCity.getName() +
                ", DB column name: " + cityRepository.findNameById(sourceCityId));
        System.out.println("Dest city details - ID: " + destCity.getId() +
                ", Name: " + destCity.getName() +
                ", DB column name: " + cityRepository.findNameById(destCityId));

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
                AdjacencyMap destMap = route.getDestinationMap() != null ? cityToMapMapping.get(route.getDestinationMap().getOriginCity()): null;
                if (destMap != null) {  // Safety check
                    sourceMap.addDestination(destMap, route.getCost(), route.getTransportationType());
                }
            }
        }

        return graph;
    }

    private PathResultDTO convertToDTO(Graph.PathResult result) {
        if (!result.isPathFound()) {
            return new PathResultDTO(
                    false,
                    result.getTransportationType(),
                    Integer.MAX_VALUE,
                    new ArrayList<>(),
                    new ArrayList<>()
            );
        }

        List<Long> cityIds = result.getPath().stream()
                .map(City::getId)
                .collect(Collectors.toList());

        List<RouteDTO> routes = new ArrayList<>();
        for (int i = 0; i < result.getPath().size() - 1; i++) {
            String sourceCityName = result.getPath().get(i).getName();
            String destinationCityName = result.getPath().get(i + 1).getName();
            String type = result.getTransportationType();
            Integer cost = routeRepository.findRouteEntitiesBySourceNameDestinationNameAndTransportationType(
                    sourceCityName,
                    destinationCityName,
                    type
            ).orElseThrow(() -> new EntityNotFoundException("Route not found")).getCost();
            routes.add(new RouteDTO(
                    sourceCityName,
                    destinationCityName,
                    type,
                    cost
            ));
        }

        return new PathResultDTO(
                result.isPathFound(),
                result.getTransportationType(),
                result.getTotalDistance(),
                cityIds,
                routes
        );
    }

    public RouteDTO createRoute(RouteDTO routeDTO) {
        City sourceCity = cityRepository.findByName(routeDTO.getSourceCityName())
                .orElseThrow(() -> new EntityNotFoundException("Source city not found: " + routeDTO.getSourceCityName()));

        City destCity = cityRepository.findByName(routeDTO.getDestinationCityName())
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

        RouteEntity savedRoute = routeRepository.save(route);

        return new RouteDTO(
                sourceCity.getName(),
                destCity.getName(),
                savedRoute.getTransportationType(),
                savedRoute.getCost()
        );
    }

    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RouteDTO convertToDTO(RouteEntity route) {
        return new RouteDTO(
                route.getSourceMap().getOriginCity().getName(),
                route.getDestinationMap().getOriginCity().getName(),
                route.getTransportationType(),
                route.getCost()
        );
    }
}