package org.retal.offgame.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.retal.offgame.entity.technologies.Technology;

import java.util.List;

@Getter
@Setter
@Builder
public class TechnologyDTO {
    private Technology technology;
    private Long level;
    private ResourcesDTO researchCost;
    private Double researchTime;
    private List<RequirementDTO> requirements;
}
