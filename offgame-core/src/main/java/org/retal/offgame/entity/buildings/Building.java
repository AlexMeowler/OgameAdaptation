package org.retal.offgame.entity.buildings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.Requirement;
import org.retal.offgame.entity.Upgradeable;

import java.util.Map;
import java.util.Set;

import static java.lang.Math.pow;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id", discriminatorType = DiscriminatorType.INTEGER)
public class Building extends Upgradeable {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    @JsonIgnore
    private Set<Requirement> requirements;

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(2, level - 1);
    }

    @Override
    public Double calculateBuildingTime(Long level, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        Long robotFactoryLevel = specialBuildingLevels.get(RobotFactory.class);
        Long naniteFactoryLevel = specialBuildingLevels.get(NaniteFactory.class);
        return calculateBuildingTime(calculateBuildingCost(level)) / (robotFactoryLevel + 1) * pow(0.5, naniteFactoryLevel);
    }

    private Double calculateBuildingTime(ResourcesDTO buildingCost) {
        Double metal = buildingCost.getMetal().amount();
        Double crystal = buildingCost.getCrystal().amount();

        return 3600.0 * (metal + crystal) / 2500 * pow(0.5, 1);
    }

    public Double calculateDemolitionTime(Long level, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        return calculateBuildingTime(level, specialBuildingLevels) / 2;
    }

    protected ResourcesDTO getProductionPerHour(long level, long temperature, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        return ResourcesDTO.empty();
    }

    public ResourcesDTO getResourceInfo(long level, double efficiency, long temperature, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        ResourcesDTO resourceInfo = getProductionPerHour(level, temperature, specialBuildingLevels).multiplyBy(efficiency);
        resourceInfo.setEfficiency(efficiency);
        return resourceInfo;
    }

    //todo building activation filter with function implementation (if building should be active or not)

    protected int getMultiplier() {
        return RESOURCE_PRODUCTION_MULTIPLIER;
    }

    @Override
    public String getDetailsPath() {
        return String.format("%s/%d", BUILDING_URL_PATH, getId());
    }

    @Override
    public String getImagePath() {
        return String.format("%s/%s", BUILDING, getImageName());
    }
}
