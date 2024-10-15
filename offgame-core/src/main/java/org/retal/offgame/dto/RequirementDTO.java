package org.retal.offgame.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequirementDTO {
    private String detailsPath;
    private String imagePath;
    private Long id;
    private String name;
    private Long requiredLevel;
    private Long currentLevel;
}
