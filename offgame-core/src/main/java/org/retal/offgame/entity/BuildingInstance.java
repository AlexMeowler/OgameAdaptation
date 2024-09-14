package org.retal.offgame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.retal.offgame.entity.buildings.Building;

@Entity
@Table(name = "planet_building")
@Getter
@Setter
public class BuildingInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column
    private Long level;
}
