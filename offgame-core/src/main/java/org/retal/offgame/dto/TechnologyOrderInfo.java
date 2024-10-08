package org.retal.offgame.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyOrderInfo {

    private Long id;
    private Long technologyId;
    private Long planetId;
    private Instant endTime;
}
