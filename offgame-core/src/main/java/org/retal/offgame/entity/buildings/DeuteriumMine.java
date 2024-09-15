package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("4")
public class DeuteriumMine extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.builder()
                .deuterium(new ResourceDTO(10 * level * pow(1.1, level) * (-0.002 * temperature + 1.28) * getMultiplier()))
                .build();
    }
}
