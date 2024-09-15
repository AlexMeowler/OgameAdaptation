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
                .metal(new ResourceDTO(getMetal(), 0.0))
                .crystal(new ResourceDTO(getCrystal(), 0.0))
                .deuterium(new ResourceDTO(getDeuterium(), 0.0))
                .build();
    }

    public void updateResources(ResourcesDTO productionPerHour, Duration duration) {
        double hours = 1.0 * duration.getSeconds() / 3600;
        setMetal(getMetal() + calcResource(productionPerHour.getMetal(), hours));
        setCrystal(getCrystal() + calcResource(productionPerHour.getCrystal(), hours));
        setDeuterium(getDeuterium() + calcResource(productionPerHour.getDeuterium(), hours));
    }

    private double calcResource(ResourceDTO resourceDTO, double hours) {
        return resourceDTO.getProductionPerHour() * hours;
    }
}
