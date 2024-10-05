package org.retal.offgame.entity;

import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.buildings.Building;

import java.util.Map;

import static org.retal.offgame.dto.ResourceDTO.withAmount;

public abstract class Upgradeable {

    public ResourcesDTO calculateBuildingCost(Long level) {
        return ResourcesDTO.builder()
                .metal(withAmount(calcResource(getCostMetal(), level)))
                .crystal(withAmount(calcResource(getCostCrystal(), level)))
                .deuterium(withAmount(calcResource(getCostDeuterium(), level)))
                .energy(withAmount(calcResource(getCostEnergy(), level)))
                .build();
    }

    protected abstract Double calcResource(Long base, Long level);

    protected abstract Long getCostMetal();

    protected abstract Long getCostCrystal();

    protected abstract Long getCostDeuterium();

    protected abstract Long getCostEnergy();

    public abstract Double calculateBuildingTime(Long level, Map<Class<? extends Building>, Long> specialBuildingLevels);
}
