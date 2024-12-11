package com.hacks.hackathon.city;

import com.hacks.hackathon.resources.Medical;
import com.hacks.hackathon.resources.Food;
import com.hacks.hackathon.resources.Water;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityName;
    private int population;
    private int xAxis;
    private int yAxis;
    private int fuel;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Water water;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Medical medical;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Food food;
}
