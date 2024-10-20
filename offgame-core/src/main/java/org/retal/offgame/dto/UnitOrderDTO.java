package org.retal.offgame.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitOrderDTO {

    @NotNull
    private Long planetId;
    @NotNull
    private Long unitId;
    @NotNull
    private Long amount;

}
