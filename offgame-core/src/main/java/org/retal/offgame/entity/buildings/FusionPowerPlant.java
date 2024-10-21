package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.technologies.EnergyTechnology;

import java.util.Map;

import static java.lang.Math.pow;
import static org.retal.offgame.dto.ResourceDTO.withProduction;

@Entity
@DiscriminatorValue("11")
public class FusionPowerPlant extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, long temperature, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        double production = 30 * level * pow(1.05 + 0.01 * specialBuildingLevels.get(EnergyTechnology.class), level);
        return ResourcesDTO.builder()
                .deuterium(withProduction(-10 * level * pow(1.1, level)))
                .energy(new ResourceDTO(production, 0.0, production))
                .build();
    }

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(1.8, level - 1);
    }
}
