package org.retal.offgame.dto;

import lombok.*;

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
    private Map<Long, ResourcesDTO> productionByLevel;
    private Map<Long, ResourcesDTO> differenceByLevel;
}
