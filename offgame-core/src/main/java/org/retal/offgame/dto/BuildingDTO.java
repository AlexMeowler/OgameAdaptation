package org.retal.offgame.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.retal.offgame.entity.buildings.Building;

@Getter
@Setter
@Builder
public class BuildingDTO {
    private Building building;
    private Long level;
    private ResourcesDTO buildingCost;
    private Double buildingTime;
    private Double energyDiff;
}
