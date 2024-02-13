package org.spacetravel.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.spacetravel.enums.Planets;

@Entity
@Table(name = "planet")
@Data
public class Planet {
    @Id
    @Enumerated(EnumType.STRING)
    private Planets id;
    @Column(name = "name")
    private String name;
    public Planet(){}
    public Planet(Planets id){
        this.id = id;
        this.name = id.getPlanetName();
    }
}
