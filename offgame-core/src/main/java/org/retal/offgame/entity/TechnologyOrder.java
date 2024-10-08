package org.retal.offgame.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "technology_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technology_instance_id", nullable = false)
    private TechnologyInstance technologyInstance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet researchingPlanet;

    @Column
    private Instant finishedAt;
}
