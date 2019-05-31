import java.awt.*;

/**
 * Extends the Shapes abstract.
 * Collects Coordinates and Colours for entry into Array.
 * Represents the Plot tool
 */
public class Plot extends Shapes {
    public Plot(float x, float y, Color cL) {
        super.type = 0;
        super.x[0] = x;
        super.y[0] = y;
        super.colourLine = cL;
    }

    public float getX() {
        return this.x[0];
    }

    public float getY() {
        return this.y[0];
    }

    public Color getColorL() {
        return this.colourLine;
    }
}