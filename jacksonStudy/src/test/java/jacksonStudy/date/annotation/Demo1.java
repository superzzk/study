package jacksonStudy.date.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public void whenUsingJsonFormatAnnotationToFormatDate_thenCorrect()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "20-12-2014 02:30:00";
        Event event = new Event("party", df.parse(toParse));

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }
}
