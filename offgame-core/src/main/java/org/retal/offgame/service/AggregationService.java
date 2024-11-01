package org.retal.offgame.service;

import org.retal.offgame.dto.PlanetOverview;

public interface AggregationService {

    PlanetOverview getPlanetOverview(Long planetId);
}
