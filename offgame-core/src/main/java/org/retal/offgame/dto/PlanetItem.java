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
}
