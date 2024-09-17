package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourcesDTO;

import static java.lang.Math.pow;
import static org.retal.offgame.dto.ResourceDTO.withAmount;
import static org.retal.offgame.dto.ResourceDTO.withProduction;

@Entity
@DiscriminatorValue("2")
public class MetalMine extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.builder()
                .metal(withProduction(30 * level * pow(1.1, level) * getMultiplier()))
                .energy(withAmount(-10 * level * pow(1.1, level)))
                .build();
    }

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(1.5, level);
    }
}
