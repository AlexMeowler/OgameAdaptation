package org.retal.offgame.scheduled;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.orders.BuildingOrder;
import org.retal.offgame.service.BuildingInstanceService;
import org.retal.offgame.service.BuildingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderScheduler {

    private final BuildingOrderService buildingOrderService;
    private final BuildingInstanceService buildingInstanceService;

    @Async
    @Scheduled(fixedRate = 1000)
    public void processOrders() {
        buildingOrderService.initCreatedOrders();

        Map<BuildingOrder, BuildingInstance> orderWithInstance = buildingOrderService.getUnprocessedOrders().stream()
                .collect(toMap(
                        Function.identity(),
                        BuildingOrder::getBuildingInstance
                ));

        processBuildingOrders(orderWithInstance.keySet());
        processBuildingInstances(orderWithInstance.values());
    }

    private void processBuildingInstances(Collection<BuildingInstance> buildingInstances) {
        buildingInstances.forEach(BuildingInstance::incrementLevel);

        buildingInstanceService.saveAll(buildingInstances);
    }

    private void processBuildingOrders(Collection<BuildingOrder> buildingOrders) {
        buildingOrderService.deleteAll(buildingOrders);
    }
}
