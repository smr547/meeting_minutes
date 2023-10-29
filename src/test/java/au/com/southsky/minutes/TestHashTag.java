import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals; 
import au.com.southsky.minutes.HashTag; 
  
public class TestHashTag {  
  
    @Test  
    public void test() {  
        HashTag ht = new HashTag("budget", "budget-pattern");

        System.out.println(ht);  
        System.out.println("Not yet implemented"); 
        assertEquals(ht.toString(),"Hashtag: #budget Pattern: budget");
         
    }  
  
}  
