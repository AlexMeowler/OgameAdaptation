package org.retal.offgame.service.util;

import org.retal.offgame.dto.RequirementDTO;
import org.retal.offgame.entity.Requirement;
import org.retal.offgame.entity.Upgradeable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public final class RequirementUtils {
    private RequirementUtils() {

    }

    public static List<RequirementDTO> getRequirements(Collection<Requirement> requirements, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        return requirements.stream()
                //.filter(requirement -> isRequirementNotMet(requirement, specialEntityLevels))
                .map(requirement -> toRequirementDTO(requirement, specialEntityLevels))
                .sorted(comparing((RequirementDTO requirementDTO) -> requirementDTO.getImagePath().split("/")[0])
                        .thenComparingLong(RequirementDTO::getId))
                .collect(Collectors.toList());
    }

    private static boolean isRequirementNotMet(Requirement requirement, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        Upgradeable requiredObject = getRequiredObject(requirement);

        return specialEntityLevels.getOrDefault(requiredObject.getClass(), 0L) < requirement.getRequiredLevel();
    }

    private static Upgradeable getRequiredObject(Requirement requirement) {
        return Optional.<Upgradeable>ofNullable(requirement.getRequiredBuilding())
                .orElse(requirement.getRequiredTechnology());
    }

    private static RequirementDTO toRequirementDTO(Requirement requirement, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        Upgradeable requiredObject = getRequiredObject(requirement);

        return RequirementDTO.builder()
                .id(requiredObject.getId())
                .detailsPath(requiredObject.getDetailsPath())
                .imagePath(requiredObject.getImagePath())
                .name(requiredObject.getName())
                .requiredLevel(requirement.getRequiredLevel())
                .currentLevel(specialEntityLevels.getOrDefault(requiredObject.getClass(), 0L))
                .build();
    }
}
