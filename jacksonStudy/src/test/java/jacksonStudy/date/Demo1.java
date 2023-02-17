package jacksonStudy.date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import jacksonStudy.ignore_properties.mix_ins.MyDtoWithSpecialField;
import jacksonStudy.ignore_properties.mix_ins.MyMixInForIgnoreType;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    public void whenSerializingDateWithJackson_thenSerializedToTimestamp()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = df.parse("01-01-1970 01:00");
        Event event = new Event("party", date);

        ObjectMapper mapper = new ObjectMapper();
        String eventString = mapper.writeValueAsString(event);
        System.out.println(eventString);
    }

    @Test
    public void whenSerializingDateToISO8601_thenSerializedToText()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "01-01-1970 02:30";
        Date date = df.parse(toParse);
        Event event = new Event("party", date);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // StdDateFormat is ISO8601 since jackson 2.9
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
        String result = mapper.writeValueAsString(event);
        assertThat(result, containsString("1970-01-01T02:30:00.000+00:00"));
    }

    @Test
    public void whenSettingObjectMapperDateFormat_thenCorrect()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");

        String toParse = "20-12-2014 02:30";
        Event event = new Event("party", df.parse(toParse));

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        String result = mapper.writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenDeserializingDateWithJackson_thenCorrect()
            throws JsonProcessingException, IOException {

        String json = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(df);

        Event event = mapper.readerFor(Event.class).readValue(json);
        assertEquals("20-12-2014 02:30:00", df.format(event.getEventDate()));
    }
}
