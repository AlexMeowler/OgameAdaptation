package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "planet")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    @Column(insertable = false, updatable = false)
    private Integer minTemperature;

    @Column(insertable = false, updatable = false)
    private Integer maxTemperature;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "planet")
    @JsonIgnore
    private Set<BuildingInstance> buildings;

    @Column
    @CreatedDate
    private Instant createdAt;

    @OneToOne(mappedBy = "planet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Resources resources;

    public Integer averageTemperature() {
        return (getMinTemperature() + getMaxTemperature()) / 2;
    }
}
