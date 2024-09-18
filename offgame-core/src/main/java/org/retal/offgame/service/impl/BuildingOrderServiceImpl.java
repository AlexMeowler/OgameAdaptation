package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.BuildingOrder;
import org.retal.offgame.repository.BuildingOrderRepository;
import org.retal.offgame.service.BuildingOrderService;
import org.retal.offgame.service.AbstractCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static org.retal.offgame.entity.BuildingOrder.Status.started;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildingOrderServiceImpl extends AbstractCrudService<BuildingOrder> implements BuildingOrderService {

    private final BuildingOrderRepository buildingOrderRepository;

    @Override
    public Collection<BuildingOrder> getUnprocessedPresentOrders() {
        return buildingOrderRepository.findPresentOrdersByStatus(started);
    }

    @Override
    protected CrudRepository<BuildingOrder, ?> getRepository() {
        return buildingOrderRepository;
    }
}
