package org.retal.offgame.dto;

import lombok.*;

import java.util.function.Function;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.retal.offgame.dto.ResourceDTO.withMaxAmount;
import static org.retal.offgame.dto.ResourceDTO.withProduction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResourcesDTO {

    private ResourceDTO metal;
    private ResourceDTO crystal;
    private ResourceDTO deuterium;
    private ResourceDTO energy;

    public Double calculateProductionEffectiveness() {
        ResourceDTO totalEnergy = getEnergy();
        Double energyCurrent = totalEnergy.amount();
        Double energyConsumed = totalEnergy.maxAmount() - energyCurrent;

        return min(1.0, max(0.0, 1 - energyCurrent / energyConsumed));
    }

    public static ResourcesDTO.ResourcesDTOBuilder builder() {
        return new ResourcesDTOBuilder()
                .metal(ResourceDTO.empty())
                .crystal(ResourceDTO.empty())
                .deuterium(ResourceDTO.empty())
                .energy(ResourceDTO.empty());
    }

    public static ResourcesDTO empty() {
        return ResourcesDTO.builder().build();
    }

    public static ResourcesDTO defaultDTO() {
        ResourcesDTO defaultProduction = builder()
                .metal(withProduction(1200.0))
                .crystal(withProduction(600.0))
                .deuterium(withProduction(300.0))
                .energy(ResourceDTO.empty())
                .build();
        ResourcesDTO defaultMaxAmount = builder()
                .metal(withMaxAmount(10000.0)) //todo use static method for lvl 0 when add storages?
                .crystal(withMaxAmount(10000.0))
                .deuterium(withMaxAmount(10000.0))
                .build();

        return defaultProduction.merge(defaultMaxAmount);
    }

    public ResourcesDTO merge(ResourcesDTO change) {
        setMetal(merge(change, ResourcesDTO::getMetal));
        setCrystal(merge(change, ResourcesDTO::getCrystal));
        setDeuterium(merge(change, ResourcesDTO::getDeuterium));
        setEnergy(merge(change, ResourcesDTO::getEnergy));

        return this;
    }

    private ResourceDTO merge(ResourcesDTO change, Function<ResourcesDTO, ResourceDTO> getter) {
        return getter.apply(this).merge(getter.apply(change));
    }

    public ResourcesDTO negate() {
        setMetal(negate(ResourcesDTO::getMetal));
        setCrystal(negate(ResourcesDTO::getCrystal));
        setDeuterium(negate(ResourcesDTO::getDeuterium));
        setEnergy(negate(ResourcesDTO::getEnergy));

        return this;
    }

    private ResourceDTO negate(Function<ResourcesDTO, ResourceDTO> getter) {
        return getter.apply(this).negate();
    }
}
