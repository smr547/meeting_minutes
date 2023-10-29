import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals; 
import au.com.southsky.minutes.HashTag;
import au.com.southsky.minutes.InvalidHashTagException; 
  
public class TestHashTag {  
  
    @Test  
    public void testToString() {  
        HashTag ht = new HashTag("budget", "budget-pattern");
        assertEquals(ht.toString(),"Hashtag: #budget Pattern: budget");   
    }  

    @Test
    public void testLoadList() throws InvalidHashTagException{
        
        HashTag.loadTagList(this.getClass().getResourceAsStream("known.tags"));
    }
  
}  
