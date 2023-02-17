package jacksonStudy.ignore_properties.property_level;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyDto {

    private String stringValue;
    @JsonIgnore
    private int intValue;
    private boolean booleanValue;

    public MyDto() {
        super();
    }

    // standard setters and getters are not shown
}