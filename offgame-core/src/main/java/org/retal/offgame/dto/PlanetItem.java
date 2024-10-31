package org.retal.offgame.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetItem {

    private Long id;
    private String name;
    private String imageName;
    private Long usedFields;
    private Long totalFields;
    private Long minTemperature;
    private Long maxTemperature;
    private Long galaxy;
    private Long system;
    private Long position;
}
