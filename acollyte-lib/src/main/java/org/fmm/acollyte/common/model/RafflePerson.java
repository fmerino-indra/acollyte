package org.fmm.acollyte.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * The persistent class for the service_person database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "selected_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name="raffle_person")
@NamedQuery(name="RafflePerson.findAll", query="SELECT s FROM RafflePerson s")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class, 
        property = "id")

public class RafflePerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Integer id;

	@Column(name="can_go")
	private Boolean canGo;

	@Column(name="has_gone")
	private Boolean hasGone;

	@Column(name="have_gone")
	private Boolean haveGone;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person person;

	//bi-directional many-to-one association to Substitution
	@OneToMany(mappedBy="rafflePerson")
	@JsonIgnore
	private List<Substitution> substitutions;

    @ManyToOne
    @JoinColumn(name="raffle_id")
    private Raffle raffle;

    private Integer random;
    
	public RafflePerson() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCanGo() {
		return this.canGo;
	}

	public void setCanGo(Boolean canGo) {
		this.canGo = canGo;
	}

	public Boolean getHasGone() {
		return this.hasGone;
	}

	public void setHasGone(Boolean hasGone) {
		this.hasGone = hasGone;
	}

	public Boolean getHaveGone() {
		return this.haveGone;
	}

	public void setHaveGone(Boolean haveGone) {
		this.haveGone = haveGone;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Substitution> getSubstitutions() {
		return this.substitutions;
	}

	public void setSubstitutions(List<Substitution> substitutions) {
		this.substitutions = substitutions;
	}

	public Substitution addSubstitution(Substitution substitution) {
		getSubstitutions().add(substitution);
		substitution.setRafflePerson(this);

		return substitution;
	}

	public Substitution removeSubstitution(Substitution substitution) {
		getSubstitutions().remove(substitution);
		substitution.setRafflePerson(null);

		return substitution;
	}
	@Override
	public String toString() {
	    return this.person.toString() + "(" + this.raffle.getDate() + ")";
	}

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public Integer getRandom() {
        return random;
    }

    public void setRandom(Integer random) {
        this.random = random;
    }
}