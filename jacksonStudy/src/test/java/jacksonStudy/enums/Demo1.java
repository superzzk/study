package jacksonStudy.enums;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public void default_representation () throws JsonProcessingException {

        String enumString = new ObjectMapper().writeValueAsString(Distance.MILE);
        System.out.println(enumString);//MILE
    }

}
