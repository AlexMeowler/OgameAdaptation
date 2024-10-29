package org.retal.offgame.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class ResourceDTO {
    private Double amount;
    private Double productionPerHour;
    private Double maxAmount;

    public static ResourceDTO withProduction(Double productionPerHour) {
        return new ResourceDTO(0.0, productionPerHour, 0.0);
    }

    public static ResourceDTO withAmount(Double amount) {
        return new ResourceDTO(amount, 0.0, 0.0);
    }

    public static ResourceDTO withMaxAmount(Double maxAmount) {
        return new ResourceDTO(0.0, 0.0, maxAmount);
    }

    public static ResourceDTO empty() {
        return new ResourceDTO(0.0, 0.0, 0.0);
    }

    public ResourceDTO merge(ResourceDTO change) {
        Double newAmount = addValues(amount(), change.amount());
        Double newProductionPerHour = addValues(productionPerHour(), change.productionPerHour());
        Double newMaxAmount = addValues(maxAmount(), change.maxAmount());
        return new ResourceDTO(newAmount, newProductionPerHour, newMaxAmount);
    }

    private Double addValues(Double... values) {
        return addValues(Double::sum, 0.0, values);
    }

    private <T> T addValues(BinaryOperator<T> reductor, T base, T... values) {
        return Stream.of(values)
                .reduce(base, reductor);
    }

    public ResourceDTO negate() {
        return new ResourceDTO(-amount(), -productionPerHour(), -maxAmount());
    }

    public ResourceDTO multiplyBy(double multiplier) {
        return new ResourceDTO(amount() * multiplier, productionPerHour() * multiplier, maxAmount() * multiplier);
    }

    public int compareTo(Function<ResourceDTO, Double> getter, ResourceDTO target) {
        return getter.apply(this).compareTo(getter.apply(target));
    }

    @JsonIgnore
    public boolean isEmpty() {
        return Stream.of(amount, productionPerHour, maxAmount)
                .map(x -> x == 0.0)
                .reduce(true, Boolean::logicalAnd);
    }

    public Double amount() {
        return amount;
    }

    public Double productionPerHour() {
        return productionPerHour;
    }

    public Double maxAmount() {
        return maxAmount;
    }

}