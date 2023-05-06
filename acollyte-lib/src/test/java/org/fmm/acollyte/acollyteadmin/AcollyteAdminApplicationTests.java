package org.fmm.acollyte.acollyteadmin;

import java.util.List;

import org.fmm.acollyte.common.model.Person;
import org.fmm.acollyte.common.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.Assert;

@SpringBootTest
@EntityScan(basePackages = {"org.fmm.acollyte.common"})
@EnableJpaRepositories(basePackages = {"org.fmm.acollyte.common","org.fmm.acollyte.acollyteadmin"})
class AcollyteAdminApplicationTests {
    @Autowired
    PersonRepository personRepository;

//	@Test
	void testJPQL() {
	}

//	@Test
	void listPersonsJPQL() {
		List<Person> personas = null;
		personas = personRepository.listAllPerson();
		Assert.notNull(personas, "La lista de personas es vacía");
	}
	
}
