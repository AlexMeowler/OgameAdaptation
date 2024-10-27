package org.retal.offgame.dto;

import lombok.*;
import org.retal.offgame.entity.units.EngineType;
import org.retal.offgame.entity.units.UnitType;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitDetails {

    private Long id;
    private String name;
    private String imageName;
    private String description;
    private Long hull;
    private Long shields;
    private Long attack;
    private Long capacity;
    private EngineType engineType;
    private UnitType unitType;
    private Long speed;
    private Long fuelConsumption;
    private List<RequirementDTO> requirements;
}
