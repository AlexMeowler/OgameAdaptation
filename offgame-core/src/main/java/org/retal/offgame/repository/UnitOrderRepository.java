package org.retal.offgame.repository;

import org.retal.offgame.entity.UnitInstance;
import org.retal.offgame.entity.orders.OrderStatus;
import org.retal.offgame.entity.orders.UnitOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UnitOrderRepository extends JpaRepository<UnitOrder, Long> {

    @Query("select uo from UnitOrder uo " +
            "where uo.status = org.retal.offgame.entity.orders.OrderStatus.started and uo.unitFinishedAt < instant")
    Set<UnitOrder> findUnprocessedFinishedOrders();

    List<UnitOrder> findUnitOrdersByUnitInstancePlanetIdAndStatusInOrderByCreatedAt(Long planetId, Set<OrderStatus> statuses);

    @Query("select uo from UnitOrder uo " +
            "join uo.unitInstance ui " +
            "where ui = :unitInstance and uo.status = org.retal.offgame.entity.orders.OrderStatus.created " +
            "order by uo.createdAt desc " +
            "limit 1")
    Optional<UnitOrder> findLatestActiveOrderForBuildingInstance(@Param("unitInstance") UnitInstance unitInstance);

    @Query(value = "with id_with_row as (" +
            "select uo.id, uo.status, ROW_NUMBER() OVER(PARTITION BY ui.planet_id ORDER BY created_at ASC) as row_number " +
            "from unit_order uo " +
            "join unit_instance ui on ui.id = uo.unit_instance_id " +
            "where status in ('created', 'started') " +
            ") " +
            "select * from unit_order uo " +
            "where id in (" +
            "select id from id_with_row where row_number = 1 and status = 'created')", nativeQuery = true)
    List<UnitOrder> getUnitCandidatesToInit();
}
