package org.retal.offgame.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public class ResourceDTO {

    private final Double amount;

    private final Double productionPerHour;

    public ResourceDTO(Double productionPerHour) {
        this(0.0, productionPerHour);
    }

    public static ResourceDTO empty() {
        return new ResourceDTO(0.0, 0.0);
    }

    public ResourceDTO merge(ResourceDTO change) {
        Double newAmount = addValues(getAmount(), change.getAmount());
        Double newProductionPerHour = addValues(getProductionPerHour(), change.getProductionPerHour());
        return new ResourceDTO(newAmount, newProductionPerHour);
    }

    private Double addValues(Double... values) {
        return addValues(Double::sum, 0.0, values);
    }

    private <T> T addValues(BinaryOperator<T> reductor, T base, T... values) {
        return Stream.of(values)
                .reduce(base, reductor);
    }
}