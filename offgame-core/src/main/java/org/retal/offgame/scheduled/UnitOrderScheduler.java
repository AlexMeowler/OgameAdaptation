package org.retal.offgame.scheduled;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.entity.orders.UnitOrder;
import org.retal.offgame.service.UnitInstanceService;
import org.retal.offgame.service.UnitOrderService;
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
public class UnitOrderScheduler {

    private final UnitOrderService unitOrderService;
    private final UnitInstanceService unitInstanceService;

    @Async
    @Scheduled(fixedRate = 1000)
    public void processOrders() {
        unitOrderService.initCreatedOrders();

        Map<UnitOrder, UnitInstance> orderWithInstance = unitOrderService.getUnprocessedOrders().stream()
                .collect(toMap(
                        Function.identity(),
                        UnitOrder::getUnitInstance
                ));

        processUnitOrders(orderWithInstance.keySet());
        processUnitInstances(orderWithInstance.values());
    }

    private void processUnitInstances(Collection<UnitInstance> unitInstances) {
        unitInstances.forEach(UnitInstance::incrementAmount);

        unitInstanceService.saveAll(unitInstances);
    }

    private void processUnitOrders(Collection<UnitOrder> unitOrders) {
        unitOrders.stream()
                .map(UnitOrder::nextUnit)
                .forEach(uo -> {
                    if (uo.getAmountLeft() > 0) {
                        unitOrderService.saveOrUpdate(uo);
                    } else {
                        unitOrderService.deleteById(uo.getId());
                    }
                });

        unitOrderService.deleteAll(unitOrders);
    }
}
