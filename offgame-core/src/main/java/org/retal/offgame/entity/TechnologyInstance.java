package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.retal.offgame.entity.orders.TechnologyOrder;
import org.retal.offgame.entity.technologies.Technology;

@Entity
@Table(name = "technology_instance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "technology_id", nullable = false)
    @JsonIgnore
    private Technology technology;

    @Column(name = "technology_id", insertable = false, updatable = false)
    private Long technologyId;

    @Column
    private Long level;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "technologyInstance")
    @JsonIgnore
    private TechnologyOrder order;

    public TechnologyInstance incrementLevel() {
        setLevel(getLevel() + 1);
        return this;
    }
}
