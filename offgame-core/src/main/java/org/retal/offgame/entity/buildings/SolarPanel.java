package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("1")
public class SolarPanel extends Building {

    @Override
    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        double production = 20 * level * pow(1.1, level);
        return ResourcesDTO.builder()
                .energy(new ResourceDTO(production, 0.0, production))
                .build();
    }

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(1.5, level - 1);
    }
}
