package jacksonStudy.inheritance.no_arg_constructors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
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
        mapper.readValue(jsonDataString, Fleet.class);
    }
}
