import java.awt.*;

public class Rectangle extends Shapes {
    public Rectangle(int x1, int y1, int x2, int y2, Color c)
    {
        super.type = 2;
        super.colour = c;
        super.x[0] = x1;
        super.y[0] = y1;
        super.x[1] = x2;
        super.y[1] = y2;
    }
}