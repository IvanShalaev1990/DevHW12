package org.spacetravel.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Data
@ToString(exclude = "tickets")
@EqualsAndHashCode(of = "id")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "clientId",fetch =  FetchType.EAGER ,cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    public Client() {}
    public Client(String name) {
        this.name = name;
    }

    public Client(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
