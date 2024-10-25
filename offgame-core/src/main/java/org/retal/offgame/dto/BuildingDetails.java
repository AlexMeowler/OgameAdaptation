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

    private Long id;
    private String name;
    private String imageName;
    private String description;
    private Long currentLevel;
    private ResourcesDTO destructionCost;
    private Long destructionTime;
    private Map<Long, ResourcesDTO> productionByLevel;
    private Map<Long, ResourcesDTO> differenceByLevel;
    private List<RequirementDTO> requirements;
}
