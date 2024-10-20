package org.retal.offgame.entity.technologies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class EnergyTechnology extends Technology {
}
