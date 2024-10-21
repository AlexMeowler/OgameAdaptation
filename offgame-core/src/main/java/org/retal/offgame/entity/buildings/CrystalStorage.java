package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Upgradeable;

import java.util.Map;
import java.util.function.Function;

import static java.lang.Math.exp;
import static org.retal.offgame.dto.ResourceDTO.withMaxAmount;

@Entity
@DiscriminatorValue("9")
public class CrystalStorage extends Building implements Storage {

    @Override
    public ResourcesDTO getProductionPerHour(long level, long temperature, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        return ResourcesDTO.builder()
                .crystal(withMaxAmount(Math.floor(2.5 * exp(20.0 * level / 33)) * 5000))
                .build();
    }

    @Override
    public Function<ResourcesDTO, ResourceDTO> extractResourceInfo() {
        return ResourcesDTO::getCrystal;
    }
}
