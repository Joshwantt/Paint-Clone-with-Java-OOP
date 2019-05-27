import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.io.PrintWriter;


import static javax.swing.UIManager.get;

/**
 * The PaintCanvas class gathers mouse click coordinates,
 * colours and shape data in order to display a series
 * of real-time 2D graphical effects.
 * <p>
 * Can be broken down into three sections.
 * </p>
 * <p>
 * <b> One:</b> Detects mouse clicks and button presses
 * to determine shape size and type.
 * </p>
 * <p>
 * <b>Two:</b> Saves most recent shape to an ArrayList
 * in a pre-determined format
 * </p>
 * <p>
 * <b>Three:</b> Shapes in the ArrayList are read and
 * drawn in real-time by the 2D graphics library.
 * </p>
 *
 */
public class PaintCanvas extends JPanel {
    private ArrayList<Shapes> entries = new ArrayList(); //The main array for storing entries of shapes, complete with coordinates and colours
    private mouseHandler mouse = new mouseHandler(); // Construction of mouseHandler
    private actionHandler acts = new actionHandler(); // Construction of actionHandler
    private GetHex retHex = new GetHex(); //Construction of returnHex

    private static final Color BG = Color.WHITE;
    int x1, x2, y1, y2; //x1,y1 represent the x,y coordinates gathered when clicking the mouse IN, x2,y2 represent the mouse being released.
    private Color colour = new Color(0, 0, 0);
    private int shape = 0; //The shape selected in accordance with the pen tool selected
    private Color colourLine = Color.BLACK; // The colour of shape outlines
    private Color colourFill = Color.WHITE; // The colour of shape fills
    private boolean fill; //Whether or not a shape has a fill

    private JPanel pnlBtns = new JPanel(); //The top panel full of buttons
    private JPanel pnlCanvas = new JPanel(); //The main painting canvas where graphics are drawn
    private JButton btnPlot = new JButton("PLOT"); //Plot
    private JButton btnLine = new JButton("LINE"); //Line
    private JButton btnRect = new JButton("RECTANGLE"); //Rectangle
    private JButton btnOval = new JButton("OVAL"); //Oval
    private JButton btnCol = new JButton("  "); //Colour
    private JButton btnColF = new JButton("  "); //Fill Colour
    private JCheckBox btnFill = new JCheckBox("Fill"); //Choose whether or not shape gets Fill Colour
    private JButton btnSave = new JButton("SAVE"); //Save

    /**
     * Builds and formats the requisite components.
     * These components consist of buttons and
     * listeners.
     */
    public PaintCanvas() {
        this.btnPlot.addActionListener(acts);
        this.btnLine.addActionListener(acts);
        this.btnRect.addActionListener(acts);
        this.btnOval.addActionListener(acts);
        this.btnFill.addActionListener(acts);
        this.btnCol.addActionListener(acts);
        this.btnColF.addActionListener(acts);
        this.btnSave.addActionListener(acts);
        this.pnlBtns.add(this.btnPlot);
        this.pnlBtns.add(this.btnLine);
        this.pnlBtns.add(this.btnRect);
        this.pnlBtns.add(this.btnOval);
        this.pnlBtns.add(this.btnCol);
        this.pnlBtns.add(this.btnColF);
        this.pnlBtns.add(this.btnFill);
        this.pnlBtns.add(this.btnSave);
        this.btnCol.setBackground(Color.BLACK); //Set default colours for Fill and Line colours.
        this.btnColF.setBackground(Color.WHITE);

        this.add(this.pnlBtns, "North");
        this.add(this.pnlCanvas, "Center");
        this.addMouseListener(this.mouse);
        nullBtns(); //set background colour of all buttons (excepting Col and ColF) to null.
        btnPlot.setBackground(Color.CYAN); //Set background of Plot to Cyan as it is selected by default.
    }

    /**
     * Converts integer X coordinates into float values.
     *
     * @param width the width of the screen in int format.
     * @return
     */
    public float encodeX(int width) { //encode pixel location to percentage of total screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        float decimal = (width/pixels);
        return decimal; //Return a percentage of the screen width
    }

    /**
     * Converts integer Y coordinates into float values.
     *
     * @param height the height of the screen in int format.
     * @return
     */
    public float encodeY(int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        float decimal = (height/pixels);
        return decimal;//Return a percentage of the screen height
    }

    /**
     * Converts the encoded float values into integer coordinates.
     *
     * @param width the width of the screen in float format.
     * @return
     */
    public int decodeX(float width) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        int location = (int)Math.round(width*pixels);
        return location;//Convert Screen width percentage into pixel integer
    }
    /**
     * Converts the encoded float values into integer coordinates.
     *
     * @param height the height of the screen in float format.
     * @return
     */
    public int decodeY(float height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        int location = (int)Math.round(height*pixels);
        return location;//Convert Screen height percentage into pixel integer
    }

    /**
     * Overrides the paintComponent in JComponent to draw shapes from an array.
     * <p>
     * Determines what tool is in use via the 'shape' int.
     * Depending on the selected tool, Overrides paintComponent
     * with shape-appropriate instructions.
     * Runs through the ArrayList to draw or fill every listed shape.
     *
     * @param g Graphics component
     */
    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight()); //upon initialisation, clear all graphics from the screen

        if (!entries.isEmpty()) {
            for (int i = 0; i < entries.size(); ++i) {// for loop runs through every shape in entries and draws them to the canvas
                g.setColor((entries.get(i)).colourLine);// Set the pen colour to the current shape's colour
                /*if ((entries.get(i)).colourLine != g.getColor()) {
                    g.setColor((entries.get(i)).colourLine);
                }*/

                if ((entries.get(i)).type == 0) {// if entry i is of PLOT type
                    g.setColor((entries.get(i)).colourLine);//set colour according to the current entry being read
                    g.fillRect(decodeX(entries.get(i).x[0])-5, decodeY(entries.get(i).y[0])-5,10,10);// Draw Filled Square 10*10 pixels wide centered on mouse pointer
                }
                else if ((entries.get(i)).type == 1) {// if entry i is of LINE type
                    g.setColor((entries.get(i)).colourLine);
                    g.drawLine(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1]), decodeY(entries.get(i).y[1]));// Draw line starting where mouse was clicked in, ending where it was released
                }
                else if ((entries.get(i)).type == 2) {// if entry i is of Square type
                    if (!entries.get(i).fill) {// if "no fill" was selected
                        g.setColor((entries.get(i)).colourLine);
                        g.drawRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                    }
                    else if (entries.get(i).fill){
                        g.setColor(entries.get(i).colourLine);
                        g.drawRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                        g.setColor(entries.get(i).colourFill);

                        //The following if statements are for formatting the filler rectangle based on positive and negative x,y coords
                        if (((entries.get(i).x[1] - entries.get(i).x[0]) > 0) && (entries.get(i).y[1] - entries.get(i).y[0]) > 0) { // Positive X and Y coords
                            g.fillRect(decodeX(entries.get(i).x[0]) + 1, decodeY(entries.get(i).y[0]) + 1, (decodeX(entries.get(i).x[1] - entries.get(i).x[0])) - 1, decodeY(entries.get(i).y[1] - entries.get(i).y[0]) - 1);
                        } else if (((entries.get(i).x[1] - entries.get(i).x[0]) < 0) && (entries.get(i).y[1] - entries.get(i).y[0]) > 0) { // Negative X Positive Y
                            g.fillRect(decodeX(entries.get(i).x[0]) -1, decodeY(entries.get(i).y[0]) + 1, (decodeX(entries.get(i).x[1] - entries.get(i).x[0])) +3, decodeY(entries.get(i).y[1] - entries.get(i).y[0]) -1);
                        } else if (((entries.get(i).x[1] - entries.get(i).x[0]) < 0) && (entries.get(i).y[1] - entries.get(i).y[0]) < 0) { // Negative X and Y
                            g.fillRect(decodeX(entries.get(i).x[0]) - 1, decodeY(entries.get(i).y[0]) - 1, (decodeX(entries.get(i).x[1] - entries.get(i).x[0])) +3, decodeY(entries.get(i).y[1] - entries.get(i).y[0]) +3 );
                        }
                        else if (((entries.get(i).x[1] - entries.get(i).x[0]) > 0) && (entries.get(i).y[1] - entries.get(i).y[0]) < 0) {// Positive X Negative Y
                            g.fillRect(decodeX(entries.get(i).x[0]) + 1, decodeY(entries.get(i).y[0]) - 1, (decodeX(entries.get(i).x[1] - entries.get(i).x[0])) -1, decodeY(entries.get(i).y[1] - entries.get(i).y[0]) +3);
                        }

                    }
                }
                else if ((this.entries.get(i)).type == 3) {
                    if (!entries.get(i).fill) { //
                        g.drawOval(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                    } else {
                        g.setColor((this.entries.get(i)).colourLine);
                        g.drawOval(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                        g.setColor((this.entries.get(i)).colourFill);
                        //various numbers have been tweaked so that the filler oval does not overlap the underlying outline (z-fighting)
                        g.fillOval(decodeX(entries.get(i).x[0])+1, decodeY(entries.get(i).y[0])+1, decodeX(entries.get(i).x[1] - entries.get(i).x[0])-2, decodeY(entries.get(i).y[1] - entries.get(i).y[0])-2);
                    }
                }
            }
        }
    }

    /**
     * mouseHandler is an implementation of the MouseListener class.
     * listens for mouse presses and mouse releases in order to collect
     * and save coordinates to an array
     */
    private class mouseHandler implements MouseListener {
        private mouseHandler() {

        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }

        /**
         * Collects x and y coordinates when mouse is pressed.
         *
         * @param me mouseEvent listens for mouse clicks
         */
        public void mousePressed(MouseEvent me) {
            x1 = me.getX();
            y1 = me.getY();
            repaint();
        }

        /**
         * Adds a new entry to entries based on the selected shape
         * Collects finishing x and y coordinates when mouse is released
         *
         * @param me mouseEvent listens for mouse clicks
         */
        public void mouseReleased(MouseEvent me) {

            if (btnFill.isSelected()) {
                fill = true;
            } else {
                fill = false;
            }

            switch (PaintCanvas.this.shape) {
                case 0:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Plot(encodeX(x2), encodeY(y2), colourLine));
                    repaint();
                    break;
                case 1:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Line(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2), colourLine));
                    repaint();
                    break;
                case 2:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Rectangle(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2),fill, colourFill,colourLine));
                    repaint();
                    break;
                case 3:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Oval(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2),fill, colourFill,colourLine));
                    repaint();
                    break;

            }
        }

        public void mouseClicked(MouseEvent me) {

        }


    }

    /**
     * Sets the background colour of all buttons to null
     */
    private void nullBtns() {
        btnPlot.setBackground((Color)null);
        btnLine.setBackground((Color)null);
        btnRect.setBackground((Color)null);
        btnOval.setBackground((Color)null);
    }

    private class actionHandler implements ActionListener {
        private actionHandler() {
        }

        /**
         * Defines the actions of each button on the buttonPanel
         * <p>
         * Sets the value of the shape int and changes the colour
         * of the active button
         * <p>
         * Opens the colourPicker.
         *
         * @param a
         */
        public void actionPerformed(ActionEvent a) {
            if (a.getSource()== btnPlot){ //If the Plot button is selected
                shape = 0; // Set shape to 0
                nullBtns(); // Set all buttons to a null background colour
                btnPlot.setBackground(Color.CYAN); //set the Plot button to cyan
            }
            if (a.getSource()== btnLine){
                shape = 1;
                nullBtns();
                btnLine.setBackground(Color.CYAN);
            }

            if (a.getSource()== btnRect){
                shape = 2;
                nullBtns();
                btnRect.setBackground(Color.CYAN);
            }
            if (a.getSource()== btnOval){
                shape = 3;
                nullBtns();
                btnOval.setBackground(Color.CYAN);
            }
            if (a.getSource() == btnCol) {
                String hexL = "#"+Integer.toHexString(colourLine.getRGB()).substring(2);
                //Open the colour picker dialogue box
                Color c = JColorChooser.showDialog(null,"Primary Colour",Color.BLACK,false);
                btnCol.setBackground(c); //set the Background of the Colour Button to the selected colour.
                //entries.add(new ColorFill(c));
                colourLine = c; //Publish the selected colour
            }
            if (a.getSource() == btnColF) {
                Color c2 = JColorChooser.showDialog(null,"Secondary Colour",Color.WHITE,false);
                btnColF.setBackground(c2);
                //entries.add(new ColorFill(c2));
                colourFill = c2;
            }

            if (a.getSource() == btnSave) { //save code
                Color colHoldL = Color.BLACK;
                Color colHoldF = Color.WHITE;

                try (PrintWriter out = new PrintWriter("save.vec")) {
                    if (!entries.isEmpty()) {
                        for (int i = 0; i < entries.size(); ++i) {

                                /*
                                if (entries.get(i).colourLine != entries.get(i-1).colourLine && (i>0)) {
                                    Color c = entries.get(i).colourLine;
                                    String line = String.format("PEN #%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                                    out.println(line);
                                }

                                if (entries.get(i).colourFill != entries.get(i-1).colourFill) {
                                    Color c = entries.get(i).colourFill;
                                    String line = String.format("FILL #%02X%02X%02X", c.getRed(), c.getGreen(), c.getBlue());
                                    out.println(line);
                                }
                                */

                            if (entries.get(i).type == 0) {
                                if (entries.get(i).colourLine != colHoldL){
                                    colHoldL = entries.get(i).colourLine;
                                    out.println("PEN "+retHex.returnHex(colHoldL));
                                }
                                String line = String.format("PLOT %6f %6f",entries.get(i).x[0],entries.get(i).y[0]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 1) {
                                if (entries.get(i).colourLine != colHoldL){
                                    colHoldL = entries.get(i).colourLine;
                                    out.println("PEN "+retHex.returnHex(colHoldL));
                                }
                                String line = String.format("LINE %6f %6f %6f %6f",entries.get(i).x[0],entries.get(i).y[0],entries.get(i).x[1],entries.get(i).y[1]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 2) {
                                if (entries.get(i).colourLine != colHoldL){
                                    colHoldL = entries.get(i).colourLine;
                                    out.println("PEN "+retHex.returnHex(colHoldL));
                                }
                                if (entries.get(i).colourFill != colHoldF){
                                    colHoldF = entries.get(i).colourFill;
                                    out.println("FILL "+retHex.returnHex(colHoldF));
                                }
                                String line = String.format("RECTANGLE %6f %6f %6f %6f",entries.get(i).x[0],entries.get(i).y[0],entries.get(i).x[1],entries.get(i).y[1]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 3) {
                                if (entries.get(i).colourLine != colHoldL){
                                    colHoldL = entries.get(i).colourLine;
                                    out.println("PEN "+retHex.returnHex(colHoldL));
                                }
                                if (entries.get(i).colourFill != colHoldF){
                                    colHoldF = entries.get(i).colourFill;
                                    out.println("FILL "+retHex.returnHex(colHoldF));
                                }
                                String line = String.format("ELLIPSE %6f %6f %6f %6f",entries.get(i).x[0],entries.get(i).y[0],entries.get(i).x[1],entries.get(i).y[1]);
                                out.println(line);
                            }

                        }
                    }

                }
                catch (IOException ex) {
                    //somethings gone wrong with save
                }
            }
        }
    }
}

