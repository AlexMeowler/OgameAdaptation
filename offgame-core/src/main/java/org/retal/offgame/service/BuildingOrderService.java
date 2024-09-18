package org.retal.offgame.service;

import org.retal.offgame.entity.BuildingOrder;

import java.util.Collection;

public interface BuildingOrderService extends CrudService<BuildingOrder> {

    Collection<BuildingOrder> getUnprocessedPresentOrders();
}
