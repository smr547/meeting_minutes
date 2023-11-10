package au.com.southsky.minutes;

import java.util.Date;
import au.com.southsky.minutes.HashTag;

public class HashTagValue {
    private HashTag hashTag;
    private String value;
    private Date date;

    public HashTagValue(HashTag ht, String val, Date d) {
        this.hashTag = ht;
        this.value = val;
        this.date = d;
    }

    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "HashTagValue for " + this.hashTag.getName()+" has value: " + this.value;
    }
}
