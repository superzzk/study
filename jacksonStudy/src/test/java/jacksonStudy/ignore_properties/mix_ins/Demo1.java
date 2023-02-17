package jacksonStudy.ignore_properties.mix_ins;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jacksonStudy.inheritance.conversion.Car;
import jacksonStudy.inheritance.conversion.Truck;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public final void givenFieldTypeIsIgnored_whenDtoIsSerialized_thenCorrect()
            throws JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(String[].class, MyMixInForIgnoreType.class);
        MyDtoWithSpecialField dtoObject = new MyDtoWithSpecialField();
        dtoObject.setBooleanValue(true);

        String dtoAsString = mapper.writeValueAsString(dtoObject);

        assertThat(dtoAsString, containsString("intValue"));
        assertThat(dtoAsString, containsString("booleanValue"));
        assertThat(dtoAsString, not(containsString("stringValue")));
    }
}
