package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.PathResultDTO;
import com.hacks.hackathon.DijkstraAlgorithm.RouteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RouteController {
    private final RouteService routeService;

    // Route endpoints
    @PostMapping()
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(routeService.createRoute(routeDTO));
    }

    @GetMapping()
    public ResponseEntity<List<RouteDTO>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    // Path finding endpoints
    @GetMapping("/shortest-path/{sourceCityId}/{destCityId}/{transportationType}")
    public ResponseEntity<PathResultDTO> findShortestPath(
            @PathVariable Long sourceCityId,
            @PathVariable Long destCityId,
            @PathVariable String transportationType) {
        return ResponseEntity.ok(
                routeService.findShortestPath(sourceCityId, destCityId, transportationType));
    }
}