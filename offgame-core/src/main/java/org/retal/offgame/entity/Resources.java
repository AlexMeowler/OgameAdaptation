package org.retal.offgame.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.time.Instant;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.retal.offgame.dto.ResourceDTO.withAmount;

@Entity
@Table(name = "planet_resource")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Resources {

    @Id
    @JsonIgnore
    private Long id;

    @Column
    private Double metal;

    @Column
    private Double crystal;

    @Column
    private Double deuterium;

    @Column
    @JsonIgnore
    @LastModifiedDate
    private Instant updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Planet planet;

    public ResourcesDTO toDTO() {
        return ResourcesDTO.builder()
                .metal(withAmount(getMetal()))
                .crystal(withAmount(getCrystal()))
                .deuterium(withAmount(getDeuterium()))
                .build();
    }

    public void updateResources(ResourcesDTO total, Duration duration) {
        double hours = 1.0 * duration.getSeconds() / 3600;
        Double productionEffectiveness = total.calculateProductionEffectiveness();
        setMetal(calcResource(getMetal(), total.getMetal(), hours, productionEffectiveness));
        setCrystal(calcResource(getCrystal(), total.getCrystal(), hours, productionEffectiveness));
        setDeuterium(calcResource(getDeuterium(), total.getDeuterium(), hours, productionEffectiveness));
    }

    private double calcResource(Double current, ResourceDTO resourceDTO, double hours, double effectiveness) {
        double newValue = current + resourceDTO.productionPerHour() * hours * effectiveness;
        return max(current, min(resourceDTO.maxAmount(), newValue));
    }
}
