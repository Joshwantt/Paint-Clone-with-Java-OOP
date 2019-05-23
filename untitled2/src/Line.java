import java.awt.*;

public class Line extends Shapes {
    public Line(double x1, double y1, double x2, double y2, Color c) {
        super.type = 1;
        super.colour = c;
        super.x[0] = x1;
        super.y[0] = y1;
        super.x[1] = x2;
        super.y[1] = y2;
    }
}