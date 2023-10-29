package au.com.southsky.minutes;

public class HashTag {
    private String tagName;
    private String pattern;

    public HashTag(String tagName, String pattern) {
        this.tagName = tagName;
        this.pattern = pattern;
    }

    public String toString() {
        return "Hashtag: #"+this.tagName+" Pattern: "+this.tagName;
    }
}
