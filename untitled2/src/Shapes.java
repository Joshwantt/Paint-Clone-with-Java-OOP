import java.awt.*;

/**
 *  Abstract class responsible for constructing shapes when invoked by subclasses:
 *  Plot, Line, Rectangle, Oval, Polygon.
 */
public abstract class Shapes {
    int type; // The type of shape
    float[] x = new float[3]; // X coordinate
    float[] y = new float[3]; // Y coordinate
    boolean fill; // Whether or not the shape has a Fill colour or is transparent

    Color colourFill; // Colour of shape's fill, see boolean fill
    Color colourLine; // Colour of shape's line

    public Shapes() {
    }


}
