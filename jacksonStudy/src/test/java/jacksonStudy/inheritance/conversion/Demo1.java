package jacksonStudy.inheritance.conversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public void test1() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Car car = new Car("Mercedes-Benz", "S500", 5, 250.0);
        Truck truck = mapper.convertValue(car, Truck.class);

        assertEquals("Mercedes-Benz", truck.getMake());
        assertEquals("S500", truck.getModel());
    }
}
