package jacksonStudy.enums.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public void test1() throws JsonProcessingException {

        String enumString = new ObjectMapper().writeValueAsString(Distance.MILE);
        System.out.println(enumString);//{"unit":"miles","meters":1609.34}
    }

    //@JsonValue
    @Test
    public void test2() throws JsonProcessingException {

        String enumString = new ObjectMapper().writeValueAsString(Distance2.MILE);
        System.out.println(enumString);//1609.34
    }

    //@JsonProperty
    @Test
    public void test3() throws JsonProcessingException {

        String enumString = new ObjectMapper().writeValueAsString(Distance3.MILE);
        System.out.println(enumString);//"distance-in-miles"
    }

    //@JsonCreator
    @Test
    public void test4() throws IOException {
        String jsonStr = "{\"distance\":{\"unit\":\"miles\",\"meters\":1609.34}}";
        Distance4 dis = new ObjectMapper().readValue(jsonStr, Distance4.class);
        System.out.println(dis);
    }

}
