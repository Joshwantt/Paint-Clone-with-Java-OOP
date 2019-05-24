import java.awt.*;

public class Oval extends Shapes {
    public Oval(double x1, double y1, double x2, double y2, boolean f)
    {
        super.type = 3;
        super.x[0] = x1;
        super.y[0] = y1;
        super.x[1] = x2;
        super.y[1] = y2;
        super.fill = f;
    }
}