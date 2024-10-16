package org.retal.offgame.entity;

import org.retal.offgame.dto.ResourcesDTO;

import java.util.Map;

import static org.retal.offgame.dto.ResourceDTO.withAmount;

public abstract class Upgradeable {

    protected static final String BUILDING = "building";
    protected static final String BUILDING_URL_PATH = "buildings";
    protected static final String TECHNOLOGY = "technology";
    protected static final String TECHNOLOGY_URL_PATH = "research";

    public ResourcesDTO calculateBuildingCost(Long level) {
        return ResourcesDTO.builder()
                .metal(withAmount(calcResource(getCostMetal(), level)))
                .crystal(withAmount(calcResource(getCostCrystal(), level)))
                .deuterium(withAmount(calcResource(getCostDeuterium(), level)))
                .energy(withAmount(calcResource(getCostEnergy(), level)))
                .build();
    }

    public abstract Long getId();

    public abstract String getName();

    public abstract String getDetailsPath();

    public abstract String getImagePath();

    protected abstract Double calcResource(Long base, Long level);

    protected abstract Long getCostMetal();

    protected abstract Long getCostCrystal();

    protected abstract Long getCostDeuterium();

    protected abstract Long getCostEnergy();

    public abstract Double calculateBuildingTime(Long level, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels);
}
