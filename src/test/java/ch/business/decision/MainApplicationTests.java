package ch.business.decision;

import ch.business.decision.models.Person;
import ch.business.decision.repositories.PersonRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("feature1")
class MainApplicationTests {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private Runnable feature;

	@BeforeEach
	void setUp() {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");
		this.repository.save(person);
	}

	@Test
	void feature1_readonlyRepository() {

		feature.run();

		List<Person> persons = this.repository.findAll();

		System.out.println("Test");
	}

}
