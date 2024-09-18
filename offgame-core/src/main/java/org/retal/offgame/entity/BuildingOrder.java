package org.retal.offgame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Table(name = "building_order")
@Getter
@Setter
public class BuildingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_building_id", nullable = false)
    private BuildingInstance buildingInstance;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @CreatedDate
    private Instant createdAt;

    @Column
    private Instant finishedAt;

    public enum Status {
        started,
        finished,
        cancelled
    }
}
