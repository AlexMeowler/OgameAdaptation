package org.retal.offgame.entity.technologies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.retal.offgame.dto.ResourcesDTO;
import org.retal.offgame.entity.Requirement;
import org.retal.offgame.entity.TechnologyInstance;
import org.retal.offgame.entity.Upgradeable;
import org.retal.offgame.entity.buildings.ResearchLaboratory;

import java.util.Map;
import java.util.Set;

import static java.lang.Math.pow;

@Entity
@Table(name = "technology")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "id", discriminatorType = DiscriminatorType.INTEGER)
public class Technology extends Upgradeable {

    public static final int RESOURCE_PRODUCTION_MULTIPLIER = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(insertable = false, updatable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technology")
    @JsonIgnore
    private Set<TechnologyInstance> instances;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technology")
    @JsonIgnore
    private Set<Requirement> requirements;

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(2, level - 1);
    }

    @Override
    public Double calculateBuildingTime(Long level, Map<Class<? extends Upgradeable>, Long> specialBuildingLevels) {
        Long researchLabLevel = specialBuildingLevels.get(ResearchLaboratory.class);
        return calculateBuildingTime(calculateBuildingCost(level)) / (researchLabLevel + 1);
    }

    private Double calculateBuildingTime(ResourcesDTO buildingCost) {
        Double metal = buildingCost.getMetal().amount();
        Double crystal = buildingCost.getCrystal().amount();

        return 3600.0 * (metal + crystal) / 1000 * pow(0.5, 1);
    }

    @Override
    public String getDetailsPath() {
        return String.format("%s/%d", TECHNOLOGY_URL_PATH, getId());
    }

    @Override
    public String getImagePath() {
        return String.format("%s/%s", TECHNOLOGY, getImageName());
    }

}
