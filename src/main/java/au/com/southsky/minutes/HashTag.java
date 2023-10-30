package au.com.southsky.minutes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HashTag {

    public static ArrayList <HashTag> tagList;
    private String tagName;
    private Pattern pattern;

    public HashTag(String tagName, String regex) {
        this.tagName = tagName;
        this.pattern = Pattern.compile(regex);
    }

    public String toString() {
        return "Hashtag: #"+this.tagName;
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
                if (fields.length != 1) {
                    throw new InvalidHashTagException("Expected one fields in: " + line);
                }
                String tagName = extractTagName(line);
                String pattern = line.split("=")[1];
                tagList.add(new HashTag(tagName, pattern));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractTagName(String pattern) throws InvalidHashTagException {

        if (!pattern.startsWith("#")) {
            throw new InvalidHashTagException("pattern must start with '#' at " + pattern);
        }
        if (!pattern.contains("=")) {
            throw new InvalidHashTagException("pattern must contain an '=' at " + pattern);
        }
        return pattern.split("=")[0].substring(0);
    }
}
