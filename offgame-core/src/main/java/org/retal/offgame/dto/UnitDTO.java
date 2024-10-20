package org.retal.offgame.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.retal.offgame.entity.units.Unit;

import java.util.List;

@Getter
@Setter
@Builder
public class UnitDTO {
    private Unit unit;
    private Long amount;
    private ResourcesDTO buildingCost;
    private Double buildingTime;
    private List<RequirementDTO> requirements;
}
