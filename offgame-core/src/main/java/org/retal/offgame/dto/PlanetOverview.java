package org.retal.offgame.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanetOverview {

    private Instant serverTime;
    private String name;
    private String imageName;
    private Long diameter;
    private Long usedFields;
    private Long totalFields;
    private TechnologyOrderInfo activeTechnologyOrder;
    private BuildingOrderInfo activeBuildingOrder;
    private Long spaceYardTotalTimeLeft;
    private Long minTemperature;
    private Long maxTemperature;
    private Long galaxy;
    private Long system;
    private Long position;
}
