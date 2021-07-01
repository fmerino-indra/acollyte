package org.fmm.acollyte.acollyteadmin.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.model.Service;

public class AvailabilityDTO {
    private OffsetDateTime date = null;
    private Service service = null;
    private List<Person> candidates = null;
    private Integer nCandidates = null;
    
    public AvailabilityDTO(Service service, List<Person> candidates) {
        super();
        this.service = service;
        this.date = service.getServiceDate();
        this.candidates = candidates;
        if (candidates != null)
            nCandidates = candidates.size();
    }
    
    public OffsetDateTime getDate() {
        return date;
    }
    public List<Person> getCandidates() {
        return candidates;
    }
    public Integer getnCandidates() {
        return nCandidates;
    }
    public Service getService() {
        return service;
    }
}
