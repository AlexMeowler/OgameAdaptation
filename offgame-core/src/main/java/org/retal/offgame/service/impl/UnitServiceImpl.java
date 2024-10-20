package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.dto.UnitDTO;
import org.retal.offgame.entity.Planet;
import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.units.SolarSatellite;
import org.retal.offgame.entity.units.Unit;
import org.retal.offgame.entity.units.UnitType;
import org.retal.offgame.repository.UnitRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.PlanetService;
import org.retal.offgame.service.UnitService;
import org.retal.offgame.service.util.RequirementUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UnitServiceImpl extends AbstractCrudService<Unit, Long> implements UnitService {

    private final UnitRepository unitRepository;
    private final PlanetService planetService;

    private static final String PRODUCTION = "production";

    @Override
    @Transactional
    public List<UnitDTO> getPlanetUnits(Long planetId, UnitType unitType) {
        Planet planet = planetService.getPlanetInfo(planetId);
        Set<UnitInstance> unitInstances = planet.getUnits();
        Map<Class<? extends Upgradeable>, Long> specialEntityLevels = planetService.getSpecialEntityLevels(planetId);

        return unitInstances.stream()
                .filter(unitInstance -> unitInstance.getUnit().getUnitType() == unitType)
                .sorted(comparingLong(UnitInstance::getUnitId))
                .map(unitInstance -> toDTO(unitInstance, specialEntityLevels))
                .collect(Collectors.toList());
    }

    private UnitDTO toDTO(UnitInstance unitInstance, Map<Class<? extends Upgradeable>, Long> specialEntityLevels) {
        int temperature = unitInstance.getPlanet().getMaxTemperature();
        Unit unit = unitInstance.getUnit();
        parseDescription(unit, Map.of(PRODUCTION, unit.getProduction(temperature)));
        //TODO dynamic engine type (small transport, bomber)
        return UnitDTO.builder()
                .unit(unit)
                .amount(unitInstance.getAmount())
                .buildingCost(unit.getBuildingCost())
                .buildingTime(unit.calculateBuildingTime(specialEntityLevels))
                .requirements(RequirementUtils.getRequirements(unit.getRequirements(), specialEntityLevels))
                .build();
    }

    private void parseDescription(Unit unit, Map<String, Object> args) {
        if(unit instanceof SolarSatellite) {
            Long energy = ((ResourcesDTO) args.get(PRODUCTION)).getEnergy().amount().longValue();
            String formattedDescription = String.format(unit.getShortDescription(), energy);
            unit.setShortDescription(formattedDescription);
        }
    }



    @Override
    protected CrudRepository<Unit, Long> getRepository() {
        return unitRepository;
    }
}
