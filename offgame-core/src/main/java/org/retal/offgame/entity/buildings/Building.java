package org.retal.offgame.entity.buildings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.dto.ResourceDTO;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.BuildingInstance;

import java.util.Set;

@Entity
@Table(name = "building")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id", discriminatorType = DiscriminatorType.INTEGER)
//TODO аннотация которая считывает при проверке считывает значение из дискриминатора, пихает имя класса в enum с константой
public abstract class Building {

    public static final int RESOURCE_PRODUCTION_MULTIPLIER = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(insertable=false, updatable=false)
    private Long id;

    @Column
    private String name;

    @Column(name = "description_short")
    private String shortDescription;

    @Column(name = "description_full")
    private String fullDescription;

    @Column
    private Long costMetal;

    @Column
    private Long costCrystal;

    @Column
    private Long costDeuterium;

    @Column
    private Long costEnergy;

    @Column
    private String imageName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "building")
    @JsonIgnore
    private Set<BuildingInstance> instances;

    public ResourcesDTO getProductionPerHour(long level, int temperature) {
        return ResourcesDTO.builder()
                .metal(ResourceDTO.empty())
                .crystal(ResourceDTO.empty())
                .deuterium(ResourceDTO.empty())
                .build();
    }

    protected int getMultiplier() {
        return RESOURCE_PRODUCTION_MULTIPLIER;
    }

}
