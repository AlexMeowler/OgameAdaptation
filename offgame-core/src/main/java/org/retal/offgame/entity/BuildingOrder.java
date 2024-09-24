package org.retal.offgame.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "building_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "building_instance_id", nullable = false)
    private BuildingInstance buildingInstance;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private Long orderValue;

    @Column
    private Instant createdAt;

    @Column
    private Long duration;

    @Column
    private Instant finishedAt;

    public enum Status {
        created,
        started
    }
}
