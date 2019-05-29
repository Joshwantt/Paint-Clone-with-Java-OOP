import org.junit.jupiter.api.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestJunit {

    static Rectangle rect;
    static Plot plot;
    static Line line;
    static Oval oval;

    @BeforeAll
    static void before(){
        rect = new Rectangle(1,2,3,4,false, Color.WHITE,Color.WHITE);
        System.out.println("RECT created");
        plot = new Plot(1,2,Color.WHITE);
        System.out.println("PLOT created");
        line = new Line(1,2,3,4,Color.WHITE);
        System.out.println("Line created");
        oval = new Oval(1,2,3,4,true,Color.WHITE,Color.WHITE);
        System.out.println("Line created");
    }

    //RECTANGLE

    @Test
    public void testRectangleX() {
        float[] x = {1,3};
        assertEquals(x, rect.getX());
    }

    @Test
    public void testRectangleY() {
        float[] y = {2,4};
        assertEquals(y, rect.getY());
    }

    @Test
    public void testRectangleFill() {
        assertEquals(false, rect.getFill());
    }

    @Test
    public void testRectangleColorL() {
        assertEquals(Color.WHITE, rect.getColorL());
    }
    @Test
    public void testRectangleColorF() {
        assertEquals(Color.WHITE, rect.getColorF());
    }

    //PLOT


    @Test
    public void testPlotX() {
        assertEquals(1, plot.getX());
    }

    @Test
    public void testPlotY() {
        assertEquals(2, plot.getY());
    }

    @Test
    public void testPlotColorF() {
        assertEquals(Color.WHITE, plot.getColorL());
    }

    //LINE

    @Test
    public void testLineX() {
        float[] x = {1,3};
        assertEquals(x, line.getX());
    }

    @Test
    public void testLineY() {
        float[] y = {2,4};
        assertEquals(y, line.getY());
    }

    @Test
    public void testLineColorL() {
        assertEquals(Color.WHITE, line.getColorL());
    }

    //OVAL

    @Test
    public void testOvalX() {
        float[] x = {1,3};
        assertEquals(x, oval.getX());
    }

    @Test
    public void testOvalY() {
        float[] y = {2,4};
        assertEquals(y, oval.getY());
    }

    @Test
    public void testOvalFill() {
        assertEquals(true, oval.getFill());
    }

    @Test
    public void testOvalColorL() {
        assertEquals(Color.WHITE, oval.getColorL());
    }
    @Test
    public void testOvalColorF() {
        assertEquals(Color.WHITE, oval.getColorF());
    }

}
