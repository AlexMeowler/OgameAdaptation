package org.retal.offgame.entity.technologies;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import static java.lang.Math.pow;

@Entity
@DiscriminatorValue("17")
public class GravityTechnology extends Technology {

    @Override
    protected Double calcResource(Long base, Long level) {
        return base * pow(3, level - 1);
    }
}
