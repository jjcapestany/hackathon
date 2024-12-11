package com.hacks.hackathon.DijkstraAlgorithm;

import com.hacks.hackathon.DijkstraAlgorithm.AdjacencyMap.AdjacencyMap;
import com.hacks.hackathon.city.City;
import org.junit.jupiter.api.Test;

import static com.hacks.hackathon.CityTest.CityBuilder.buildCity;

public class GraphTest {
    @Test
    public void shouldCalculateShortestPath() {
        City cityA = buildCity("Austin");
        City cityB = buildCity("Houston");
        City cityC = buildCity("Dallas");
        City cityD = buildCity("Philadelphia");
        City cityE = buildCity("Richmond");
        City cityF = buildCity("DC");

        AdjacencyMap amA = new AdjacencyMap(cityA);
        AdjacencyMap amB = new AdjacencyMap(cityB);
        AdjacencyMap amC = new AdjacencyMap(cityC);
        AdjacencyMap amD = new AdjacencyMap(cityD);
        AdjacencyMap amE = new AdjacencyMap(cityE);
        AdjacencyMap amF = new AdjacencyMap(cityF);

        // Add destinations with transportation type "Air"
        amA.addDestination(amB, 5, "Air");
        amA.addDestination(amC, 3, "Air");
        amB.addDestination(amD, 4, "Air");
        amB.addDestination(amF, 8, "Air");
        amC.addDestination(amE, 10, "Air");
        amD.addDestination(amE, 10, "Air");
        amD.addDestination(amF, 1, "Air");
        amF.addDestination(amE, 5, "Air");

        amA.addDestination(amB, 10, "Land");
        amA.addDestination(amC, 15, "Land");
        amB.addDestination(amD, 12, "Land");
        amB.addDestination(amF, 1, "Land");
        amC.addDestination(amE, 10, "Land");
        amD.addDestination(amE, 2, "Land");
        amD.addDestination(amF, 1, "Land");
        amF.addDestination(amE, 5, "Land");

        amA.addDestination(amB, 10, "Sea");
        amA.addDestination(amC, 0, "Sea");
        amB.addDestination(amD, 12, "Sea");
        amB.addDestination(amF, 15, "Sea");
        amC.addDestination(amE, 10, "Sea");
        amD.addDestination(amE, 2, "Sea");
        amD.addDestination(amF, 1, "Sea");
        amF.addDestination(amE, 5, "Sea");

        Graph graph = new Graph();
        graph.addAdjacencyMap(amA);
        graph.addAdjacencyMap(amB);
        graph.addAdjacencyMap(amC);
        graph.addAdjacencyMap(amD);
        graph.addAdjacencyMap(amE);
        graph.addAdjacencyMap(amF);

        Graph.PathResult airPath = Graph.calculateShortestPathFromSourceToDestination(graph, amA, cityF, "Air");
        Graph.PathResult landPath = Graph.calculateShortestPathFromSourceToDestination(graph, amA, cityF, "Land");
        Graph.PathResult seaPath = Graph.calculateShortestPathFromSourceToDestination(graph, amA, cityF, "Sea");

        System.out.println(airPath);
        System.out.println(landPath);
        System.out.println(seaPath);

        // Print results
//        System.out.println(graph.getAdjacencyMaps());
//        for (AdjacencyMap node : graph.getAdjacencyMaps()) {
//            System.out.println("Shortest path to " + node.getOriginCity().getCityName() + ": " +
//                    node.getShortestPath() + " -> " + node +
//                    " with distance: " + node.getCost());
//        }

    }
}