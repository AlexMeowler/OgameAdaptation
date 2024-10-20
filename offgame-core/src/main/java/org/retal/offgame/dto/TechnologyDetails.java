package org.retal.offgame.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDetails {

    private String name;
    private String imageName;
    private String description;
    private List<RequirementDTO> requirements;
}
