package org.retal.offgame.dto;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public record ResourceDTO(Double amount, Double productionPerHour, Double maxAmount, Double effectiveness) {

    public ResourceDTO(Double amount, Double productionPerHour, Double maxAmount) {
        this(amount, productionPerHour, maxAmount, 1.0);
    }

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

    public int compareTo(Function<ResourceDTO, Double> getter, ResourceDTO target) {
        return getter.apply(this).compareTo(getter.apply(target));
    }
}