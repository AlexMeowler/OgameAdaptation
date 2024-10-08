package org.retal.offgame.service;

import org.retal.offgame.dto.TechnologyOrderDTO;
import org.retal.offgame.dto.TechnologyOrderInfo;
import org.retal.offgame.entity.TechnologyOrder;

import java.util.Collection;

public interface TechnologyOrderService extends CrudService<TechnologyOrder, Long> {

    Collection<TechnologyOrder> getUnprocessedOrders();

    TechnologyOrderInfo createTechnologyOrder(TechnologyOrderDTO technologyOrderDTO);

    TechnologyOrderInfo getTechnologyOrder(Long planetId);
}
