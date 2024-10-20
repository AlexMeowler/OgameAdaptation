package org.retal.offgame.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildingOrderInfo {

    private Long id;
    private Instant endTime;
    private String name;
    private Long value;
}
