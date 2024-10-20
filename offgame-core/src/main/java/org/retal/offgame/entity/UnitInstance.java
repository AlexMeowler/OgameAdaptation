package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.retal.offgame.entity.orders.UnitOrder;
import org.retal.offgame.entity.units.Unit;

import java.util.Set;

@Entity
@Table(name = "unit_instance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planet_id", nullable = false)
    @JsonIgnore
    private Planet planet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id", nullable = false)
    @JsonIgnore
    private Unit unit;

    @Column(name = "unit_id", insertable = false, updatable = false)
    private Long unitId;

    @Column
    private Long amount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unitInstance")
    @JsonIgnore
    private Set<UnitOrder> orders;

    public UnitInstance incrementAmount() {
        setAmount(getAmount() + 1);
        return this;
    }
}
