package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;

@Entity
@Table(name = "planet_resource")
@Getter
@Setter
public class Resources {

    @Id
    @JsonIgnore
    private Long id;

    @Column
    private Long metal;

    @Column
    private Long crystal;

    @Column
    private Long deuterium;

    @Column
    @JsonIgnore
    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Planet planet;
}
