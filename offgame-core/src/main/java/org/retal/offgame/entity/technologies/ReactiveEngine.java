package org.retal.offgame.entity.technologies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class ReactiveEngine extends Technology {
}
