package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("2")
public class MetalMine extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.builder()
                .metal(new ResourceDTO(30 * level * pow(1.1, level) * getMultiplier()))
                .build();
    }
}
