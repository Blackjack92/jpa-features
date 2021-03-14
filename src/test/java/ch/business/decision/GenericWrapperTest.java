package ch.business.decision;

import ch.business.decision.models.Person;
import ch.business.decision.models.PersonWrapper;
import ch.business.decision.models.Vehicle;
import ch.business.decision.models.VehicleWrapper;
import ch.business.decision.repositories.PersonWrapperRepository;
import ch.business.decision.repositories.VehicleWrapperRepository;
import ch.business.decision.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GenericWrapperTest {

    @Autowired
    private VehicleWrapperRepository vehicleWrapperRepository;

    @Autowired
    private PersonWrapperRepository personWrapperRepository;

    @BeforeEach
    void setup() {
        System.out.println(Utils.DASHES + "[START]");
    }

    @AfterEach
    void cleanup() {
        System.out.println(Utils.DASHES + "[FINISH]");
        this.vehicleWrapperRepository.deleteAll();
        this.personWrapperRepository.deleteAll();
    }

    @Test
    void storeWrappedVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("AABBCC");
        VehicleWrapper wrapper = new VehicleWrapper();
        wrapper.setContent(vehicle);

        VehicleWrapper storedWrapper = this.vehicleWrapperRepository.save(wrapper);
        assertThat(storedWrapper).isNotNull();
    }

    @Test
    void storeWrappedPerson() {
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        PersonWrapper wrapper = new PersonWrapper();
        wrapper.setContent(person);

        PersonWrapper storedWrapper = this.personWrapperRepository.save(wrapper);
        assertThat(storedWrapper).isNotNull();
    }
}
