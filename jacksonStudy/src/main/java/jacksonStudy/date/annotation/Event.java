package jacksonStudy.date.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 17:23
 **/
public class Event {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date eventDate;

    public Event(String name, Date eventDate) {
        this.name = name;
        this.eventDate = eventDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
