import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestJunit {
    
    @Test
    public void testRectangleX() {
       Rectangle rect = new Rectangle(1,2,3,4,false, Color.WHITE,Color.WHITE);
        assertEquals(1, rect.getXo());
    }
}
