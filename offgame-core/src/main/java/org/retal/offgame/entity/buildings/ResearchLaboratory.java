package org.retal.offgame.entity.buildings;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("7")
public class ResearchLaboratory extends Building{
}
