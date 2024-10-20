package org.retal.offgame.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOrderInfo {

    private Long id;
    private Instant currentUnitEndTime;
    private Long singleUnitDuration;
    private String name;
    private Long amountLeft;
}
