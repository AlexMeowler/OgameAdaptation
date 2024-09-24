package org.retal.offgame.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingOrderDTO {

    @NotNull
    private Long planetId;
    @NotNull
    private Long buildingId;

}
