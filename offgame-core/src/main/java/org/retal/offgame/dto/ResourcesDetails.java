package org.retal.offgame.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDetails {

    private String planetName;
    private List<ResourceDetail> resourceDetails;
    private ResourcesDTO totalResources;
}
