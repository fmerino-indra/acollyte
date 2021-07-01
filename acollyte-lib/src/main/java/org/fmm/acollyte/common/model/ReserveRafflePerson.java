package org.fmm.acollyte.common.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class ReserveRafflePerson extends RafflePerson {

    /**
     * 
     */
    private static final long serialVersionUID = -282145399565715711L;

}
