package org.retal.offgame.service;

import org.retal.offgame.dto.UnitOrderDTO;
import org.retal.offgame.dto.UnitOrderInfo;
import org.retal.offgame.entity.orders.UnitOrder;

import java.util.Collection;
import java.util.List;

public interface UnitOrderService extends CrudService<UnitOrder, Long> {

    Collection<UnitOrder> getUnprocessedOrders();

    void initCreatedOrders();

    UnitOrderInfo createUnitOrder(UnitOrderDTO UnitOrderDTO);

    List<UnitOrderInfo> getPlanetUnitOrders(Long planetId);
}
