package org.retal.offgame.entity.units;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

@Entity
@DiscriminatorValue("1")
public class SolarSatellite extends Unit {

    @Override
    public ResourcesDTO getProduction(int temperature) {
        double production = Math.min(1.0 * temperature / 4 + 20, 50);
        return ResourcesDTO.builder()
                .energy(new ResourceDTO(production, 0.0, production))
                .build();
    }
}
