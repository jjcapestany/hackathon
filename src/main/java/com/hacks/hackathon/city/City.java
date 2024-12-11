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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    private Integer population;
    private Double xCoord;
    private Double yCoord;
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
