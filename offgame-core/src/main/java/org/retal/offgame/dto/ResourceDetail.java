package org.retal.offgame.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDetail {

    private Long id;
    private String name;
    private Long level;
    private ResourcesDTO resources;
}
