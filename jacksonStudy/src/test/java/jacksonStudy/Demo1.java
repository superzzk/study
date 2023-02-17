package jacksonStudy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 15:07
 **/
public class Demo1 {

    @Test
    public void test1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        String carAsString = objectMapper.writeValueAsString(car);
        System.out.println(carAsString);
        objectMapper.writeValue(new File("target/car.json"), car);

        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car2 = objectMapper.readValue(json, Car.class);
        System.out.println(car2);
        Car car3 = objectMapper.readValue(new File("target/car.json"), Car.class);
        System.out.println(car3);
        Car car4 = objectMapper.readValue(new URL("file:target/car.json"), Car.class);
        System.out.println(car4);
    }

    /**
     * JsonNode
     * json array
     */
    @Test
    public void test2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();
        System.out.println(jsonNode.toString());



        String json2 = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map
                = objectMapper.readValue(json2, new TypeReference<Map<String,Object>>(){});
    }

    /**
     * ignore the new fields
     */
    @Test
    public void test3() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonString
                = "{ \"color\" : \"Black\", \"type\" : \"Fiat\", \"year\" : \"1970\" }";
        Car car = objectMapper.readValue(jsonString, Car.class);
        System.out.println(car);

        JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
        JsonNode jsonNodeYear = jsonNodeRoot.get("year");
        System.out.println(jsonNodeYear.asText());
    }

    /**
     * Handling Date Formats
     */
    @Test
    public void test4() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
        objectMapper.setDateFormat(df);
        Car car = new Car("yellow", "renault");
        Request request = new Request();
        request.setCar(car);
        request.setDatePurchased(new Date());
        String carAsString = objectMapper.writeValueAsString(request);
        // output: {"car":{"color":"yellow","type":"renault"},"datePurchased":"2016-07-03 11:43 AM CEST"}
        System.out.println(carAsString);
    }

    /**
     * Collections
     */
    @Test
    public void test5() throws IOException {
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
        Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);

        List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
    }
}
