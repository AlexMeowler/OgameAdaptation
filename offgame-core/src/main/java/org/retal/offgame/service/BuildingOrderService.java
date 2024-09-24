package org.retal.offgame.service;

import org.retal.offgame.dto.BuildingOrderDTO;
import org.retal.offgame.dto.BuildingOrderInfo;
import org.retal.offgame.entity.BuildingOrder;

import java.util.Collection;
import java.util.List;

public interface BuildingOrderService extends CrudService<BuildingOrder, Long> {

    Collection<BuildingOrder> getUnprocessedOrders();

    void initCreatedOrders();

    BuildingOrderInfo createBuildingOrder(BuildingOrderDTO buildingOrderDTO);

    List<BuildingOrderInfo> getPlanetBuildingOrders(Long planetId);
}
