package org.retal.offgame.dto;

import lombok.*;

import java.util.Map;
import java.util.function.Consumer;
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

    private Double globalEffectiveness;

    @Getter(AccessLevel.NONE)
    private final Map<Function<ResourcesDTO, ResourceDTO>, Consumer<ResourceDTO>> ACCESSOR_MAP = Map.of(
            ResourcesDTO::getMetal, this::setMetal,
            ResourcesDTO::getCrystal, this::setCrystal,
            ResourcesDTO::getDeuterium, this::setDeuterium,
            ResourcesDTO::getEnergy, this::setEnergy
    );


    public void setGlobalEffectiveness() {
        setGlobalEffectiveness(calculateGlobalEffectiveness());
    }

    private Double calculateGlobalEffectiveness() {
        ResourceDTO totalEnergy = getEnergy();
        Double energyCurrent = totalEnergy.amount();
        Double energyConsumed = totalEnergy.maxAmount() - energyCurrent;
        double effectiveness = min(1.0, max(0.0, 1 + energyCurrent / energyConsumed));
        return !Double.isNaN(effectiveness) ? effectiveness : 1.0;
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
        ACCESSOR_MAP.forEach((getter, setter) -> setter.accept(merge(change, getter)));

        return this;
    }

    private ResourceDTO merge(ResourcesDTO change, Function<ResourcesDTO, ResourceDTO> getter) {
        return getter.apply(this).merge(getter.apply(change));
    }

    public ResourcesDTO negate() {
        ACCESSOR_MAP.forEach((getter, setter) -> setter.accept(negate(getter)));

        return this;
    }

    private ResourceDTO negate(Function<ResourcesDTO, ResourceDTO> getter) {
        return getter.apply(this).negate();
    }

    public boolean isLessOrEqualThan(ResourcesDTO target) {
        return compareTo(target, -1) == -1;
    }

    public boolean isMoreOrEqualThan(ResourcesDTO target) {
        return compareTo(target, 1) == 1;
    }

    public boolean isEmpty() {
        return ACCESSOR_MAP.keySet().stream()
                .map(getter -> getter.apply(this))
                .map(ResourceDTO::isEmpty)
                .reduce(true, Boolean::logicalAnd);
    }

    private int compareTo(ResourcesDTO target, int startValue) {
        for (Function<ResourcesDTO, ResourceDTO> getter : ACCESSOR_MAP.keySet()) {
            int compareResult = getter.apply(this).compareTo(ResourceDTO::amount, getter.apply(target));

            if (compareResult != 0 && compareResult != startValue) {
                return 0;
            }
        }

        return startValue;
    }

    public ResourcesDTO copy() {
        return ResourcesDTO.empty().merge(this);
    }
}
