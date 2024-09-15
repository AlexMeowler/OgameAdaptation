package org.retal.offgame.dto;

import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResourcesDTO {

    private ResourceDTO metal;
    private ResourceDTO crystal;
    private ResourceDTO deuterium;

    public static ResourcesDTO.ResourcesDTOBuilder builder() {
        return new ResourcesDTOBuilder()
                .metal(ResourceDTO.empty())
                .crystal(ResourceDTO.empty())
                .deuterium(ResourceDTO.empty());
    }

    public static ResourcesDTO defaultProduction() {
        return builder()
                .metal(new ResourceDTO(1200.0))
                .crystal(new ResourceDTO(600.0))
                .deuterium(new ResourceDTO(300.0))
                .build();
    }

    public ResourcesDTO merge(ResourcesDTO change) {
        setMetal(merge(change, ResourcesDTO::getMetal));
        setCrystal(merge(change, ResourcesDTO::getCrystal));
        setDeuterium(merge(change, ResourcesDTO::getDeuterium));

        return this;
    }

    private ResourceDTO merge(ResourcesDTO change, Function<ResourcesDTO, ResourceDTO> getter) {
        return getter.apply(this).merge(getter.apply(change));
    }
}
