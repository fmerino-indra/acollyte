package org.fmm.acollyte.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the service_type database table.
 * 
 */
@Entity
@Table(name="service_type")
@NamedQuery(name="ServiceType.findAll", query="SELECT s FROM ServiceType s")
public class ServiceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="service_type")
	private String serviceType;

    private Integer needed;
    private Integer nReserves;
    
	public ServiceType() {
	}

	public Integer getId() {
		return this.id;
	}

	public static ServiceType from(ServiceTypeEnum ste) {
	    ServiceType aux = null;
	    aux = new ServiceType();
	    aux.setId(ste.getId());
	    aux.setServiceType(ste.getName());
	    aux.setNeeded(1);
	    aux.setnReserves(1);
	    return aux;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    public Integer getnReserves() {
        return nReserves;
    }

    public void setnReserves(Integer nReserves) {
        this.nReserves = nReserves;
    }
}