package org.fmm.acollyte.common.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the exception database table.
 * 
 */
@Entity
@NamedQuery(name="Absence.findAll", query="SELECT e FROM Absence e")
public class Absence implements Serializable {
	private static final long serialVersionUID = 1L;

    @Column(name="day", columnDefinition = "DATE")
	private LocalDate day;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
//    @JoinColumn(name="person_id")
    private Person person;

	
	public Absence() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

}