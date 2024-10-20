package org.retal.offgame.entity.units;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Requirement;
import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.buildings.NaniteFactory;
import org.retal.offgame.entity.buildings.SpaceYard;

import java.util.Map;
import java.util.Set;

import static java.lang.Math.pow;
import static org.retal.offgame.dto.ResourceDTO.withAmount;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id", discriminatorType = DiscriminatorType.INTEGER)
public class Unit {

    public static final int RESOURCE_PRODUCTION_MULTIPLIER = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(insertable = false, updatable = false)
    private Long id;

    @Column
    private String name;

    @Column(name = "description_short")
    private String shortDescription;

    @Column(name = "description_full")
    private String fullDescription;

    @Column
    private String imageName;

    @Column
    private Long costMetal;

    @Column
    private Long costCrystal;

    @Column
    private Long costDeuterium;

    @Column
    private Long costEnergy;

    @Column
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column
    private Long hull;

    @Column
    private Long shields;

    @Column
    private Long attack;

    @Column
    private Long capacity;

    @Column
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column
    private Long speed;

    @Column
    private Long fuelConsumption;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    @JsonIgnore
    private Set<UnitInstance> instances;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    @JsonIgnore
    private Set<Requirement> requirements;

    public ResourcesDTO getBuildingCost() {
        return ResourcesDTO.builder()
                .metal(withAmount(1.0 * getCostMetal()))
                .crystal(withAmount(1.0 * getCostCrystal()))
                .deuterium(withAmount(1.0 * getCostDeuterium()))
                .energy(withAmount(1.0 * getCostEnergy()))
                .build();
    }

    public Double calculateBuildingTime(Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        Long spaceYardLevel = specialBuildingLevels.get(SpaceYard.class);
        Long naniteFactoryLevel = specialBuildingLevels.get(NaniteFactory.class);
        return calculateBuildingTime(getBuildingCost()) / (spaceYardLevel + 1) * pow(0.5, naniteFactoryLevel);
    }

    private Double calculateBuildingTime(ResourcesDTO buildingCost) {
        Double metal = buildingCost.getMetal().amount();
        Double crystal = buildingCost.getCrystal().amount();

        return 3600.0 * (metal + crystal) / 2500 * pow(0.5, 1);
    }

    public ResourcesDTO getProduction(int temperature) {
        return ResourcesDTO.empty();
    }
}
