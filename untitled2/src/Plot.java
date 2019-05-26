import java.awt.*;

/**
 * Extends the Shapes abstract.
 * Collects Coordinates and Colours for entry into Array.
 * Represents the Plot tool
 */
public class Plot extends Shapes {
    public Plot(float x, float y) {
        super.type = 0;
        super.x[0] = x;
        super.y[0] = y;
    }
}