package org.retal.offgame.repository;

import org.retal.offgame.entity.orders.TechnologyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TechnologyOrderRepository extends JpaRepository<TechnologyOrder, Long> {

    @Query("select to from TechnologyOrder to " +
            "where to.finishedAt < instant")
    Set<TechnologyOrder> findUnprocessedFinishedOrders();

    Optional<TechnologyOrder> findTechnologyOrderByTechnologyInstanceOwnerId(Long userId);
}
