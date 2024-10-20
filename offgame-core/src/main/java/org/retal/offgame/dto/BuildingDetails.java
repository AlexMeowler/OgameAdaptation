package org.retal.offgame.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDetails {

    private String name;
    private String imageName;
    private String description;
    private Long currentLevel;
    private Map<Long, ResourcesDTO> productionByLevel;
    private Map<Long, ResourcesDTO> differenceByLevel;
    private List<RequirementDTO> requirements;
}
