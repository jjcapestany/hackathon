package com.hacks.hackathon.CityTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hacks.hackathon.city.City;
import com.hacks.hackathon.city.CityRepo;
import com.hacks.hackathon.city.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Mock
    CityRepo cityRepo;
    @Autowired
    private CityService cityService;

    @Test
    public void createCityTest() throws Exception {
        City testCity = City.builder().cityName("Turtleback Island").population(30000).build();
        cityRepo.saveAll(List.of(testCity));
            when(cityRepo.save(testCity)).thenReturn(testCity);
        mockMvc.perform(get("/api/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value(testCity.getCityName()))
                .andExpect(jsonPath("$[0].population").value(testCity.getPopulation()));
    }
}

