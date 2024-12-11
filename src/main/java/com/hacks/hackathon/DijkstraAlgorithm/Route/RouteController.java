package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.PathResultDTO;
import com.hacks.hackathon.DijkstraAlgorithm.RouteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    @GetMapping("/shortest-path")
    public ResponseEntity<PathResultDTO> findShortestPath(
            @RequestParam Long sourceCityId,
            @RequestParam Long destCityId,
            @RequestParam String transportationType) {
        PathResultDTO result = routeService.findShortestPath(sourceCityId, destCityId, transportationType);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO) {
        RouteDTO created = routeService.createRoute(routeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}