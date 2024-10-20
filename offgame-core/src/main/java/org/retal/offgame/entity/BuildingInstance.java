package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.orders.BuildingOrder;

import java.util.Set;

@Entity
@Table(name = "building_instance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id", nullable = false)
    @JsonIgnore
    private Planet planet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id", nullable = false)
    @JsonIgnore
    private Building building;

    @Column(name = "building_id", insertable = false, updatable = false)
    private Long buildingId;

    @Column
    private Long level;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "buildingInstance")
    @JsonIgnore
    private Set<BuildingOrder> orders;

    public BuildingInstance incrementLevel() {
        setLevel(getLevel() + 1);
        return this;
    }
}
