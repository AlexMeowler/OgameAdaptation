package org.retal.offgame.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.entity.buildings.Building;
import org.retal.offgame.entity.technologies.Technology;
import org.retal.offgame.entity.units.Unit;

@Entity
@Table(name = "requirement")
@Getter
@Setter
@NoArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technology_id")
    @JsonIgnore
    private Technology technology;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    @JsonIgnore
    private Unit unit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "required_building_id")
    @JsonIgnore
    private Building requiredBuilding;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "required_technology_id")
    @JsonIgnore
    private Technology requiredTechnology;

    @Column
    private Long requiredLevel;
}
