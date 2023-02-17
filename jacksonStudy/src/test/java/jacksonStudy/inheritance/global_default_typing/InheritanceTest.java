package jacksonStudy.inheritance.global_default_typing;

import com.fasterxml.jackson.databind.ObjectMapper;
import jacksonStudy.inheritance.global_default_typing.Car;
import jacksonStudy.inheritance.global_default_typing.Fleet;
import jacksonStudy.inheritance.global_default_typing.Truck;
import jacksonStudy.inheritance.global_default_typing.Vehicle;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

public class InheritanceTest {
    @Test
    public void test1() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        Car car = new Car("Mercedes-Benz", "S500", 5, 250.0);
        Truck truck = new Truck("Isuzu", "NQR", 7500.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(car);
        vehicles.add(truck);

        Fleet serializedFleet = new Fleet();
        serializedFleet.setVehicles(vehicles);

        String jsonDataString = mapper.writeValueAsString(serializedFleet);
        System.out.println(jsonDataString);

        Fleet deserializedFleet = mapper.readValue(jsonDataString, Fleet.class);
        assertThat(deserializedFleet.getVehicles().get(0), instanceOf(Car.class));
        assertThat(deserializedFleet.getVehicles().get(1), instanceOf(Truck.class));
    }
}
