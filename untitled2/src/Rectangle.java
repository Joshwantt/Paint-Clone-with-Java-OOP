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

        float holdX;
        float holdY;
        //Parameters for handling negative X or Y coordinates (Making a shape that does not go down and right
        if (x[0] > x[1]){
            holdX = x[0];
            x[0] = x[1];
            x[1] = holdX;
        }
        if (y[0] > y[1]){
            holdY = y[0];
            y[0] = y[1];
            y[1] = holdY;
        }
    }
    public float getXo() {
        return x[0];
    }

    public float[] getY() {
        return this.y;
    }

    public boolean getFill() {
        return this.fill;
    }

    public Color getColorF() {
        return this.colourFill;
    }

    public Color getColorL() {
        return this.colourLine;
    }
}