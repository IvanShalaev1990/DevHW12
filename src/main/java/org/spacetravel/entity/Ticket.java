package org.spacetravel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;
    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    public Ticket(int id) {
        this.id = id;
    }

    public Ticket(Client clientId, Timestamp createdAt, Planet fromPlanet, Planet toPlanet) {
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.fromPlanet = fromPlanet;
        this.toPlanet = toPlanet;
    }
}
