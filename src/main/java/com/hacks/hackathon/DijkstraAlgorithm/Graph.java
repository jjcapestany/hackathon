package com.hacks.hackathon.DijkstraAlgorithm;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMap;
import com.hacks.hackathon.city.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
public class Graph {
    private Set<AdjacencyMap> adjacencyMaps = new HashSet<>();

    public void addAdjacencyMap(AdjacencyMap nodeA) {
        adjacencyMaps.add(nodeA);
    }

    // Helper class to return path results
    @Getter
    @AllArgsConstructor
    public static class PathResult {
        private boolean pathFound;
        private String transportationType;
        private int totalDistance;
        private List<City> path;

        @Override
        public String toString() {
            if (!pathFound) {
                return "No path found";
            }
            return String.format("Path found via %s, total distance: %d, route: %s",
                    transportationType, totalDistance,
                    path.stream().map(City::getName).collect(Collectors.joining(" -> ")));
        }
    }

    public static Graph calculateShortestPathFromSource(Graph graph, AdjacencyMap source, String mean) {
        if (mean == null) {
            throw new IllegalArgumentException("Transportation mean cannot be null");
        }

        // Initialize all distances
        for (AdjacencyMap node : graph.getAdjacencyMaps()) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
        source.setCost(0);

        Set<AdjacencyMap> settledNodes = new HashSet<>();
        Set<AdjacencyMap> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            AdjacencyMap currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<AdjacencyMap, Map<String, Integer>> adjacencyPair : currentNode.getNeighborMap().entrySet()) {
                AdjacencyMap adjacentNode = adjacencyPair.getKey();
                Map<String, Integer> meanCost = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode) && meanCost.containsKey(mean)) {
                    Integer edgeWeight = meanCost.get(mean);
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static AdjacencyMap getLowestDistanceNode(Set<AdjacencyMap> unsettledNodes) {
        AdjacencyMap lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (AdjacencyMap node : unsettledNodes) {
            int nodeDistance = node.getCost();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public static PathResult calculateShortestPathFromSourceToDestination(Graph graph, AdjacencyMap source,
                                                                          City destinationCity, String mean) {
        if (mean == null) {
            throw new IllegalArgumentException("Transportation mean cannot be null");
        }

        // Initialize all distances
        for (AdjacencyMap node : graph.getAdjacencyMaps()) {
            node.setCost(Integer.MAX_VALUE);
            node.setShortestPath(new LinkedList<>());
        }
        source.setCost(0);
        // Initialize source's shortest path to empty list
        source.setShortestPath(new LinkedList<>());

        Set<AdjacencyMap> settledNodes = new HashSet<>();
        Set<AdjacencyMap> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        AdjacencyMap destinationNode = null;

        while (!unsettledNodes.isEmpty()) {
            AdjacencyMap currentNode = getLowestDistanceNode(unsettledNodes);

            // Check if we've reached the destination
            if (currentNode.getOriginCity().getName().equals(destinationCity.getName())) {
                destinationNode = currentNode;
                break;  // We found the destination, no need to continue
            }

            unsettledNodes.remove(currentNode);

            for (Map.Entry<AdjacencyMap, Map<String, Integer>> adjacencyPair :
                    currentNode.getNeighborMap().entrySet()) {
                AdjacencyMap adjacentNode = adjacencyPair.getKey();
                Map<String, Integer> meanCost = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode) && meanCost.containsKey(mean)) {
                    Integer edgeWeight = meanCost.get(mean);
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }

        if (destinationNode == null || destinationNode.getCost() == Integer.MAX_VALUE) {
            return new PathResult(false, null, Integer.MAX_VALUE, Collections.emptyList());
        }

        // Construct the final path
        List<City> path = new ArrayList<>();
        // Add the source city first
        path.add(source.getOriginCity());
        // Add intermediate cities
        path.addAll(destinationNode.getShortestPath().stream()
                .map(AdjacencyMap::getOriginCity)
                .collect(Collectors.toList()));
        // Add the destination city
        path.add(destinationNode.getOriginCity());

        return new PathResult(true, mean, destinationNode.getCost(), path);
    }

    private static void calculateMinimumDistance(AdjacencyMap evaluationNode,
                                                 Integer edgeWeight,
                                                 AdjacencyMap sourceNode) {
        int sourceDistance = sourceNode.getCost();
        if (sourceDistance + edgeWeight < evaluationNode.getCost()) {
            evaluationNode.setCost(sourceDistance + edgeWeight);
            // Create a new path list starting with all nodes up to the source
            LinkedList<AdjacencyMap> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            // Add the source node to the path only if it's not the starting node
            if (sourceNode.getCost() > 0) {
                shortestPath.add(sourceNode);
            }
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}