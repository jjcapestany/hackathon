package com.hacks.hackathon.DijkstraAlgorithm.Route;

import com.hacks.hackathon.DijkstraAlgorithm.PathResultDTO;
import com.hacks.hackathon.DijkstraAlgorithm.RouteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    void findShortestPath_ShouldReturnValidPath() throws Exception {
        PathResultDTO resultDTO = new PathResultDTO(
                true,
                "Sea",
                150,
                Arrays.asList("Coral Reef Community", "Mangrove Coastline", "Lagoon Bay Village"),
                Arrays.asList(
                        new RouteDTO(1L, "Coral Reef Community", "Mangrove Coastline", "Sea", 100),
                        new RouteDTO(2L, "Mangrove Coastline", "Lagoon Bay Village", "Sea", 50)
                )
        );

        when(routeService.findShortestPath(1L, 5L, "Sea")).thenReturn(resultDTO);

        // Perform test
        mockMvc.perform(get("/api/routes/shortest-path/1/5/Sea")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pathFound").value(true))
                .andExpect(jsonPath("$.transportationType").value("Sea"))
                .andExpect(jsonPath("$.totalDistance").value(150))
                .andExpect(jsonPath("$.cityNames[0]").value("Coral Reef Community"))
                .andExpect(jsonPath("$.cityNames[2]").value("Lagoon Bay Village"));
    }

    @Test
    void findShortestPath_WhenPathNotFound_ShouldReturnNotFound() throws Exception {
        // Prepare test data
        PathResultDTO resultDTO = new PathResultDTO(
                false,
                "Air",
                Integer.MAX_VALUE,
                List.of(),
                List.of()
        );

        when(routeService.findShortestPath(1L, 8L, "Air")).thenReturn(resultDTO);

        // Perform test
        mockMvc.perform(get("/api/routes/shortest-path/1/8/Air")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pathFound").value(false));
    }
}

