import java.awt.*;

/**
 * Extends the Shapes abstract.
 * Collects Coordinates, Colours and Fill Bool for entry into Array.
 * Represents the Rectangle tool.
 */
public class Rectangle extends Shapes {
    public Rectangle(float x1, float y1, float x2, float y2,boolean f, Color cF, Color cL)
    {
        super.type = 2;
        super.x[0] = x1;
        super.y[0] = y1;
        super.x[1] = x2;
        super.y[1] = y2;
        super.fill = f;
        super.colourFill = cF;
        super.colourLine = cL;
    }
}