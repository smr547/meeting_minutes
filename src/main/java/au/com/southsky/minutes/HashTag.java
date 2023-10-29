package au.com.southsky.minutes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HashTag {

    public static ArrayList <HashTag> tagList;
    private String tagName;
    private String pattern;

    public HashTag(String tagName, String pattern) {
        this.tagName = tagName;
        this.pattern = pattern;
    }

    public String toString() {
        return "Hashtag: #"+this.tagName+" Pattern: "+this.tagName;
    }

    public static void loadTagList(java.io.InputStream input) throws InvalidHashTagException {
        tagList = new ArrayList <HashTag>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String [] fields = line.split(" ");
                System.out.println(fields.length);
                if (fields.length != 2) {
                    throw new InvalidHashTagException("Expected two fields in: " + line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
