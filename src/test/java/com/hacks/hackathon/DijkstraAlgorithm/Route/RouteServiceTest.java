package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapEntity;
import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMapRepository;
import com.hacks.hackathon.DijkstraAlgorithm.PathResultDTO;
import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock
    private CityRepo cityRepo;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private AdjacencyMapRepository adjacencyMapRepository;

    @InjectMocks
    private RouteService routeService;

    private City coralReef;
    private City lagoonBay;
    private AdjacencyMapEntity coralReefMap;
    private AdjacencyMapEntity mangroveMap;
    private AdjacencyMapEntity lagoonBayMap;

    @BeforeEach
    void setUp() {
        coralReef = new City();
        coralReef.setId(1L);
        coralReef.setCityName("Coral Reef Community");
        coralReef.setPopulation(150000);

        City mangrove = new City();
        mangrove.setId(4L);
        mangrove.setCityName("Mangrove Coastline");
        mangrove.setPopulation(350000);

        lagoonBay = new City();
        lagoonBay.setId(5L);
        lagoonBay.setCityName("Lagoon Bay Village");
        lagoonBay.setPopulation(130000);

        coralReefMap = new AdjacencyMapEntity();
        coralReefMap.setId(1L);
        coralReefMap.setOriginCity(coralReef);

        mangroveMap = new AdjacencyMapEntity();
        mangroveMap.setId(2L);
        mangroveMap.setOriginCity(mangrove);

        lagoonBayMap = new AdjacencyMapEntity();
        lagoonBayMap.setId(3L);
        lagoonBayMap.setOriginCity(lagoonBay);
    }

    @ParameterizedTest
    @CsvSource({
            "70, 70, 2",
            "160, 150, 3"
    })
    void findShortestPath_ShouldReturnValidPath(Integer routeThreeCost, Integer expected, Integer routeSize) {
        RouteEntity route1 = new RouteEntity();
        route1.setSourceMap(coralReefMap);
        route1.setDestinationMap(mangroveMap);
        route1.setTransportationType("Sea");
        route1.setCost(100);

        RouteEntity route2 = new RouteEntity();
        route2.setSourceMap(mangroveMap);
        route2.setDestinationMap(lagoonBayMap);
        route2.setTransportationType("Sea");
        route2.setCost(50);

        RouteEntity route3 = new RouteEntity();
        route3.setSourceMap(coralReefMap);
        route3.setDestinationMap(lagoonBayMap);
        route3.setTransportationType("Sea");
        route3.setCost(routeThreeCost);

        when(cityRepo.findById(1L)).thenReturn(Optional.of(coralReef));
        when(cityRepo.findById(5L)).thenReturn(Optional.of(lagoonBay));
        when(adjacencyMapRepository.findAll()).thenReturn(Arrays.asList(coralReefMap, mangroveMap, lagoonBayMap));

        coralReefMap.setRoutes(List.of(route1, route3));
        mangroveMap.setRoutes(List.of(route2));

        PathResultDTO result = routeService.findShortestPath(1L, 5L, "Sea");

        assertThat(result.isPathFound()).isTrue();
        assertThat(result.getTransportationType()).isEqualTo("Sea");
        assertThat(result.getTotalDistance()).isEqualTo(expected);
        assertThat(result.getCityNames()).hasSize(routeSize);
        assertThat(result.getCityNames().get(0)).isEqualTo("Coral Reef Community");
        assertThat(result.getCityNames().get(routeSize - 1)).isEqualTo("Lagoon Bay Village");
    }

    @Test
    void findShortestPath_NoPathExists_ShouldReturnPathNotFound() {
        when(cityRepo.findById(1L)).thenReturn(Optional.of(coralReef));
        when(cityRepo.findById(5L)).thenReturn(Optional.of(lagoonBay));
        when(adjacencyMapRepository.findAll()).thenReturn(Arrays.asList(coralReefMap, lagoonBayMap));

        PathResultDTO result = routeService.findShortestPath(1L, 5L, "Air");

        assertThat(!result.isPathFound()).isTrue();
        assertThat(result.getCityNames()).isEmpty();
        assertThat(result.getTotalDistance()).isEqualTo(Integer.MAX_VALUE);
    }
}