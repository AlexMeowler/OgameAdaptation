package org.retal.offgame.entity.buildings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;

import java.util.Set;

import static java.lang.Math.pow;
import static org.retal.offgame.dto.ResourceDTO.withAmount;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Building {

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
    private Long costMetal;

    @Column
    private Long costCrystal;

    @Column
    private Long costDeuterium;

    @Column
    private Long costEnergy;

    @Column
    private String imageName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    @JsonIgnore
    private Set<BuildingInstance> instances;



    public ResourcesDTO calculateBuildingCost(Long level) {
        return ResourcesDTO.builder()
                .metal(withAmount(calcResource(getCostMetal(), level)))
                .crystal(withAmount(calcResource(getCostCrystal(), level)))
                .deuterium(withAmount(calcResource(getCostDeuterium(), level)))
                .energy(withAmount(calcResource(getCostEnergy(), level)))
                .build();
    }

    public Double calculateBuildingTime(Long level) {
        return calculateBuildingTime(calculateBuildingCost(level));
    }

    private Double calculateBuildingTime(ResourcesDTO buildingCost) {
        Double metal = buildingCost.getMetal().amount();
        Double crystal = buildingCost.getCrystal().amount();

        return 3600.0 * (metal + crystal) / 2500 * pow(0.5, 1);
    }

    protected Double calcResource(Long base, Long level) {
        return base * pow(2, level - 1);
    }

    protected ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.empty();
    }

    protected ResourcesDTO getMaxAmount(long level) {
        return ResourcesDTO.empty();
    }

    public ResourcesDTO getResourceInfo(long level, int temperature) {
        return getProductionPerHour(level, temperature).merge(getMaxAmount(level));
    }

    //todo building activation filter with function implementation (if building should be active or not)

    protected int getMultiplier() {
        return RESOURCE_PRODUCTION_MULTIPLIER;
    }

}
