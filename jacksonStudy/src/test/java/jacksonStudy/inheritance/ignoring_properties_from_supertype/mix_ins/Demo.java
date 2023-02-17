package jacksonStudy.inheritance.ignoring_properties_from_supertype.mix_ins;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:43
 **/
@SuppressWarnings("Duplicates")
public class Demo {
    @Test
    public void test1() throws IOException {
        abstract class CarMixIn {
            @JsonIgnore
            public String make;
            @JsonIgnore
            public String topSpeed;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Car.class, CarMixIn.class);

        Sedan sedan = new Sedan("Mercedes-Benz", "S500", 5, 250.0);
        Crossover crossover = new Crossover("BMW", "X6", 5, 250.0, 6000.0);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(sedan);
        vehicles.add(crossover);

        String jsonDataString = mapper.writeValueAsString(vehicles);
        System.out.println(jsonDataString);

        assertThat(jsonDataString, not(containsString("make")));
        assertThat(jsonDataString, containsString("model"));
        assertThat(jsonDataString, containsString("seatingCapacity"));
        assertThat(jsonDataString, not(containsString("topSpeed")));
        assertThat(jsonDataString, containsString("towingCapacity"));
    }
}
