package jacksonStudy.enums.custom_serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    public void test1 () throws IOException {

        //serialize
        String enumString = new ObjectMapper().writeValueAsString(Distance.MILE);
        System.out.println(enumString);//{"name":"MILE","unit":"miles","meters":1609.34}

        //deserialize
        Distance dis = new ObjectMapper().readValue(enumString, Distance.class);
        assertEquals(Distance.MILE, dis);
    }

}
