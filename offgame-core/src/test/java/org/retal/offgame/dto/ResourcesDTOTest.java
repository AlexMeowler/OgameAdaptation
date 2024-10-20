package org.retal.offgame.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.retal.offgame.dto.ResourceDTO.withAmount;

public class ResourcesDTOTest {

    private ResourcesDTO resourcesDTO;

    @BeforeEach
    void setUp() {
        resourcesDTO = new ResourcesDTO();
    }

    @ParameterizedTest
    @MethodSource("provideEnergyResource")
    public void testCalcResource(ResourceDTO energy, Double expected) {
        resourcesDTO.setEnergy(energy);
        resourcesDTO.setGlobalEffectiveness();

        Double effectiveness = resourcesDTO.getGlobalEffectiveness();

        Assertions.assertEquals(expected, effectiveness);
    }

    @ParameterizedTest
    @MethodSource("provideObjectsToCompare")
    public void testIsMoreOrEquals(ResourcesDTO source, ResourcesDTO target, boolean expected) {
        boolean actual = source.isMoreOrEqualThan(target);

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideEnergyResource() {
        return Stream.of(
                Arguments.of(new ResourceDTO(20.0, 0.0, 40.0), 1.0),
                Arguments.of(new ResourceDTO(0.0, 0.0, 0.0), 1.0),
                Arguments.of(new ResourceDTO(10.0, 0.0, 10.0), 1.0),
                Arguments.of(new ResourceDTO(-10.0, 0.0, 10.0), 0.5),
                Arguments.of(new ResourceDTO(-20.0, 0.0, 0.0), 0.0)
        );
    }

    private static Stream<Arguments> provideObjectsToCompare() {
        return Stream.of(
                Arguments.of(withAmounts(100.0, 100.0, 100.0, 100.0),
                        withAmounts(50.0, 50.0, 50.0, 50.0), true),
                Arguments.of(withAmounts(100.0, 100.0, 100.0, 0.0),
                        withAmounts(50.0, 100.0, 50.0, 0.0), true),
                Arguments.of(withAmounts(100.0, 100.0, 100.0, 100.0),
                        withAmounts(50.0, 150.0, 50.0, 50.0), false)
        );
    }

    private static ResourcesDTO withAmounts(Double metal, Double crystal, Double deuterium, Double energy) {
        return ResourcesDTO.builder()
                .metal(withAmount(metal))
                .crystal(withAmount(crystal))
                .deuterium(withAmount(deuterium))
                .energy(withAmount(energy))
                .build();
    }
}