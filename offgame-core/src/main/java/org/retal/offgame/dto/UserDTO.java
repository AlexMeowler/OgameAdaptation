package org.retal.offgame.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userName;
    private Long activePlanet;
}
