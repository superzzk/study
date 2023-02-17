package jacksonStudy.ignore_properties.filter;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("myFilter")
public class MyDtoWithFilter {
    private String[] stringValue;
    private int intValue;
    private boolean booleanValue;

    public String[] getStringValue() {
        return stringValue;
    }

    public void setStringValue(String[] stringValue) {
        this.stringValue = stringValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
}