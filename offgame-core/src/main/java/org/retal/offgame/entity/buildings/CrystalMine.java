package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("3")
public class CrystalMine extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.builder()
                .crystal(new ResourceDTO(20 * level * pow(1.1, level) * getMultiplier()))
                .build();
    }
}
