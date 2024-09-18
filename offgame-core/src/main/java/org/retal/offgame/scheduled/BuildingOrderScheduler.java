package org.retal.offgame.scheduled;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.BuildingOrder;
import org.retal.offgame.service.BuildingInstanceService;
import org.retal.offgame.service.BuildingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderScheduler {

    private final BuildingOrderService buildingOrderService;
    private final BuildingInstanceService buildingInstanceService;

    @Async
    @Scheduled(fixedRate = 1000)
    public void processOrders() {
        Set<BuildingInstance> instances = buildingOrderService.getUnprocessedPresentOrders().stream()
                .map(BuildingOrder::getBuildingInstance)
                .map(BuildingInstance::incrementLevel)
                .collect(toSet());

        buildingInstanceService.saveAll(instances);
    }
}
