package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "planet")
@Getter
@Setter
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planet")
    @JsonIgnore
    private Set<BuildingInstance> buildings;

    @Column
    @CreatedDate
    private OffsetDateTime createdAt;

    @OneToOne(mappedBy = "planet", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Resources resources;
}
