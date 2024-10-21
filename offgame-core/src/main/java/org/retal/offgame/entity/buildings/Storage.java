package org.retal.offgame.entity.buildings;

import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;

import java.util.function.Function;

public interface Storage {

    Function<ResourcesDTO, ResourceDTO> extractResourceInfo();
}
