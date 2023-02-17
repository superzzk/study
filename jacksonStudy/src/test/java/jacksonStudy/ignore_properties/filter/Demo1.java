package jacksonStudy.ignore_properties.filter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jacksonStudy.ignore_properties.mix_ins.MyDtoWithSpecialField;
import jacksonStudy.ignore_properties.mix_ins.MyMixInForIgnoreType;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public final void givenTypeHasFilterThatIgnoresFieldByName_whenDtoIsSerialized_thenCorrect()
            throws JsonParseException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("intValue");
        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);

        MyDtoWithFilter dtoObject = new MyDtoWithFilter();
        String dtoAsString = mapper.writer(filters).writeValueAsString(dtoObject);

        assertThat(dtoAsString, not(containsString("intValue")));
        assertThat(dtoAsString, containsString("booleanValue"));
        assertThat(dtoAsString, containsString("stringValue"));
        System.out.println(dtoAsString);
    }
}
