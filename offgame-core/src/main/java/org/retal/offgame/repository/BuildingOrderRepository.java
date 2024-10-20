package org.retal.offgame.repository;

import org.retal.offgame.entity.BuildingInstance;
import org.retal.offgame.entity.orders.BuildingOrder;
import org.retal.offgame.entity.orders.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BuildingOrderRepository extends JpaRepository<BuildingOrder, Long> {

    @Query("select bo from BuildingOrder bo " +
            "where bo.status = org.retal.offgame.entity.orders.OrderStatus.started and bo.finishedAt < instant")
    Set<BuildingOrder> findUnprocessedFinishedOrders();

    List<BuildingOrder> findBuildingOrdersByBuildingInstancePlanetIdAndStatusInOrderByCreatedAt(Long planetId, Set<OrderStatus> statuses);

    @Query("select bo from BuildingOrder bo " +
            "join bo.buildingInstance bi " +
            "where bi = :buildingInstance and bo.status = org.retal.offgame.entity.orders.OrderStatus.created " +
            "order by bo.createdAt desc " +
            "limit 1")
    Optional<BuildingOrder> findLatestActiveOrderForBuildingInstance(@Param("buildingInstance") BuildingInstance buildingInstance);

    @Query(value = "with id_with_row as (" +
            "select bo.id, bo.status, ROW_NUMBER() OVER(PARTITION BY bi.planet_id ORDER BY created_at ASC) as row_number " +
            "from building_order bo " +
            "join building_instance bi on bi.id = bo.building_instance_id " +
            "where status in ('created', 'started') " +
            ") " +
            "select * from building_order bo " +
            "where id in (" +
            "select id from id_with_row where row_number = 1 and status = 'created')", nativeQuery = true)
    List<BuildingOrder> getBuildingCandidatesToInit();
}
