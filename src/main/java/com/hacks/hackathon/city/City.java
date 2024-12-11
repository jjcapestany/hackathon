package com.hacks.hackathon.city;

import com.hacks.hackathon.resources.Medical;
import com.hacks.hackathon.resources.Food;
import com.hacks.hackathon.resources.Water;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
    public City(String name, Integer population, Integer xCoord, Integer yCoord, Long fuel, Water water, Medical medical, Food food) {
        this.name = name;
        this.population = population;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.fuel = fuel;
        this.water = water;
        this.medical = medical;
        this.food = food;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer population;
    private float xCoord;
    private float yCoord;
    private Long fuel;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Water water;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Medical medical;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Food food;
}
