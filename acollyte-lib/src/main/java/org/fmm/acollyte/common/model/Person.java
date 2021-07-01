package org.fmm.acollyte.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;

	private Integer comunidad;

	private String name;

	private String userId;
	//bi-directional many-to-one association to ServicePerson
//	@OneToMany(mappedBy="person1")
//	private List<ServicePerson> servicePersons1;

	//bi-directional many-to-one association to ServicePerson
//	@OneToMany(mappedBy="person2")
//	private List<ServicePerson> servicePersons2;

	//bi-directional many-to-one association to Substitution
//	@OneToMany(mappedBy="person")
//	private List<Substitution> substitutions;

	//bi-directional many-to-one association to EmailAccount
	@OneToOne(mappedBy="person")
	private EmailAccount emailAccount;

	//bi-directional many-to-one association to MobileNumber
	@OneToOne(mappedBy="person")
	private MobileNumber mobileNumber;

	public Person() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComunidad() {
		return this.comunidad;
	}

	public void setComunidad(Integer comunidad) {
		this.comunidad = comunidad;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<ServicePerson> getServicePersons1() {
//		return this.servicePersons1;
//	}
//
//	public void setServicePersons1(List<ServicePerson> servicePersons1) {
//		this.servicePersons1 = servicePersons1;
//	}

//	public ServicePerson addServicePersons1(ServicePerson servicePersons1) {
//		getServicePersons1().add(servicePersons1);
//		servicePersons1.setPerson1(this);
//
//		return servicePersons1;
//	}
//
//	public ServicePerson removeServicePersons1(ServicePerson servicePersons1) {
//		getServicePersons1().remove(servicePersons1);
//		servicePersons1.setPerson1(null);
//
//		return servicePersons1;
//	}

//	public List<ServicePerson> getServicePersons2() {
//		return this.servicePersons2;
//	}
//
//	public void setServicePersons2(List<ServicePerson> servicePersons2) {
//		this.servicePersons2 = servicePersons2;
//	}
//
//	public ServicePerson addServicePersons2(ServicePerson servicePersons2) {
//		getServicePersons2().add(servicePersons2);
//		servicePersons2.setPerson2(this);
//
//		return servicePersons2;
//	}
//
//	public ServicePerson removeServicePersons2(ServicePerson servicePersons2) {
//		getServicePersons2().remove(servicePersons2);
//		servicePersons2.setPerson2(null);
//
//		return servicePersons2;
//	}
//
//	public List<Substitution> getSubstitutions() {
//		return this.substitutions;
//	}

//	public void setSubstitutions(List<Substitution> substitutions) {
//		this.substitutions = substitutions;
//	}
//
//	public Substitution addSubstitution(Substitution substitution) {
//		getSubstitutions().add(substitution);
//		substitution.setPerson(this);
//
//		return substitution;
//	}
//
//	public Substitution removeSubstitution(Substitution substitution) {
//		getSubstitutions().remove(substitution);
//		substitution.setPerson(null);
//
//		return substitution;
//	}

	public EmailAccount getEmailAccount() {
		return this.emailAccount;
	}

	public void setEmailAccounts(EmailAccount emailAccount) {
		this.emailAccount = emailAccount;
	}

//	public EmailAccount addEmailAccount(EmailAccount emailAccount) {
//		getEmailAccounts().add(emailAccount);
//		emailAccount.setPerson(this);
//
//		return emailAccount;
//	}

//	public EmailAccount removeEmailAccount(EmailAccount emailAccount) {
//		getEmailAccounts().remove(emailAccount);
//		emailAccount.setPerson(null);
//
//		return emailAccount;
//	}

	public MobileNumber getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumbers(MobileNumber mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

//	public MobileNumber addMobileNumber(MobileNumber mobileNumber) {
//		getMobileNumbers().add(mobileNumber);
//		mobileNumber.setPerson(this);
//
//		return mobileNumber;
//	}
//
//	public MobileNumber removeMobileNumber(MobileNumber mobileNumber) {
//		getMobileNumbers().remove(mobileNumber);
//		mobileNumber.setPerson(null);
//
//		return mobileNumber;
//	}

//	 public String toString() {
//	     return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
//	 }

	@Override
	public String toString() {
	    return this.name;
	}
	
	@Override
    public boolean equals(Object obj) {
	    if (obj instanceof Person)
	        return this.equals((Person)obj);
	    else
	        return super.equals(obj);
    }
    public boolean equals(Person otherPerson) {
        return this.getId().equals(otherPerson.getId());
    }
    
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}