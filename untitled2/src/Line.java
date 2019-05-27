import java.awt.*;

/**
 * Extends the Shapes abstract.
 * Collects Coordinates, Colours and Fill Bool for entry into Array.
 * Represents the Line tool.
 */
public class Line extends Shapes {
    public Line(float x1, float y1, float x2, float y2, Color cL) {
        super.type = 1;
        super.x[0] = x1;
        super.y[0] = y1;
        super.x[1] = x2;
        super.y[1] = y2;
        super.colourLine = cL;
    }
}