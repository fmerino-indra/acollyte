package org.fmm.acollyte.common.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class AssignedRafflePerson extends RafflePerson {

    /**
     * 
     */
    private static final long serialVersionUID = 9023577207547469211L;
}
