import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import au.com.southsky.minutes.HashTag;
import au.com.southsky.minutes.InvalidHashTagException; 
  
public class TestHashTag {  

    private static void loadHashTagsForTest() {
        List<String> strings = new ArrayList<String>();
        strings.add("#quote=\\$?(\\d{1,3},?(\\d{3},?)*\\d{3}(\\.\\d{1,3})?|\\d{1,3}(\\.\\d{2})?)");
        strings.add("#budget=\\$?(\\d{1,3},?(\\d{3},?)*\\d{3}(\\.\\d{1,3})?|\\d{1,3}(\\.\\d{2})?)");
        strings.add("#engineHours=(\\d*\\.?\\d*)");
        strings.add("#dueDate=(\\d{1,2}\\/\\d{1,2}\\/\\d{4})");
        strings.add("#hoursDue=(\\d*\\.?\\d*)");

        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s + "\n");
        }
        try {
            HashTag.loadTagList(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testLoadFromFile() {
        try {
        HashTag.loadTagList(this.getClass().getResourceAsStream("known.tags"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testLoadTagList() {
        
        loadHashTagsForTest();
        // check for the right number of HashTags

        System.out.println("HashTagList has " + HashTag.hashTags.size() + "entries");
        assertEquals(HashTag.hashTags.size(), 5);
        assertTrue(HashTag.hashTags.containsKey("#quote"));
        assertTrue(HashTag.hashTags.containsKey("#budget"));
        assertTrue(HashTag.hashTags.containsKey("#engineHours"));
        assertTrue(HashTag.hashTags.containsKey("#dueDate"));
        assertTrue(HashTag.hashTags.containsKey("#hoursDue"));
            
    }

    @Test
    public void testBudgetValueExtraction() {
        
        loadHashTagsForTest();
        HashTag ht = HashTag.hashTags.get("#budget");
        String val = ht.getValue("#budget=$6000 ??");
        System.out.println("Budget returned " + val);
    }


    

    @Test
    public void testFindRealValue() {
        String someString = "Next engine service due by #dueDate=15/6/2024 or #hoursDue=5344";
        loadHashTagsForTest();
        // check for the right number of HashTags

        for (String k : HashTag.hashTags.keySet()) {
            System.out.println("key is " + k);
        }

        HashTag subjectTag = HashTag.hashTags.get("#hoursDue");
        String hoursDue = subjectTag.getValue(someString);
        assertEquals(hoursDue, "5344");

    }

    @Test
    public void testLoadTagListFromFile() throws InvalidHashTagException{
        
        HashTag.loadTagList(this.getClass().getResourceAsStream("known.tags"));
    }
  
}  
