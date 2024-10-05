package org.retal.offgame.entity.technologies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("10")
public class ExpeditionTechnology extends Technology {

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(3, level - 1);
    }
}
