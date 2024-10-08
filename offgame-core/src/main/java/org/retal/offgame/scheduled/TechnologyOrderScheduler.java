package org.retal.offgame.scheduled;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.TechnologyOrder;
import org.retal.offgame.service.TechnologyInstanceService;
import org.retal.offgame.service.TechnologyOrderService;
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
public class TechnologyOrderScheduler {

    private final TechnologyOrderService technologyOrderService;
    private final TechnologyInstanceService technologyInstanceService;

    @Async
    @Scheduled(fixedRate = 1000)
    public void processOrders() {

        Map<TechnologyOrder, TechnologyInstance> orderWithInstance = technologyOrderService.getUnprocessedOrders().stream()
                .collect(toMap(
                        Function.identity(),
                        TechnologyOrder::getTechnologyInstance
                ));

        processTechnologyOrders(orderWithInstance.keySet());
        processTechnologyInstances(orderWithInstance.values());
    }

    private void processTechnologyInstances(Collection<TechnologyInstance> technologyInstances) {
        technologyInstances.forEach(TechnologyInstance::incrementLevel);

        technologyInstanceService.saveAll(technologyInstances);
    }

    private void processTechnologyOrders(Collection<TechnologyOrder> technologyOrders) {
        technologyOrderService.deleteAll(technologyOrders);
    }
}
