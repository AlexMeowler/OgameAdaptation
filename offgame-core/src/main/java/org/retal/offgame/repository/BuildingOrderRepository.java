package org.retal.offgame.repository;

import org.retal.offgame.entity.BuildingOrder;
import org.retal.offgame.entity.BuildingOrder.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BuildingOrderRepository extends JpaRepository<BuildingOrder, Long> {

    @Query("select bo from BuildingOrder bo where bo.status = :status and bo.finishedAt < CURRENT_TIMESTAMP")
    Set<BuildingOrder> findPresentOrdersByStatus(@Param("status") Status status);
}
