package org.retal.offgame.entity.technologies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("8")
public class Metallurgy extends Technology {
}
