package org.retal.offgame.entity.orders;

import jakarta.persistence.*;
import lombok.*;
import org.retal.offgame.entity.UnitInstance;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "unit_order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "unit_instance_id", nullable = false)
    private UnitInstance unitInstance;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private Long amountLeft;

    @Column
    private Instant createdAt;

    @Column
    private Long singleUnitDuration;

    @Column
    private Instant unitFinishedAt;

    public UnitOrder nextUnit() {
        Instant finishedAt = Instant.now().plus(getSingleUnitDuration(), ChronoUnit.SECONDS);
        setUnitFinishedAt(finishedAt);
        setAmountLeft(getAmountLeft() - 1);
        return this;
    }
}
