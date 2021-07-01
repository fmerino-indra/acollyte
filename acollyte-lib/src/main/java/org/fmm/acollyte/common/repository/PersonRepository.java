package org.fmm.acollyte.common.repository;

import java.util.List;

import org.fmm.acollyte.common.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by felix.merino.
 * @author Félix merino
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("SELECT DISTINCT p FROM Person p "
            + " LEFT JOIN FETCH p.mobileNumber mn"
            + " LEFT JOIN FETCH p.emailAccount ea"
            + " ORDER BY p.comunidad ASC, p.id ASC")
    List<Person> listAllPerson();
    
    @Query("SELECT p FROM Person p"
            + " LEFT JOIN FETCH p.mobileNumber mn"
            + " LEFT JOIN FETCH p.emailAccount ea"
            + " WHERE p.id = :id")
    Person findFullPerson(@Param("id") Integer id);

    @Query("SELECT p FROM Person p"
            + " LEFT JOIN FETCH p.mobileNumber mn"
            + " LEFT JOIN FETCH p.emailAccount ea"
            + " WHERE p.userId = :userId")
    Person findFullPersonByUserId(@Param("userId") String userId);
}
