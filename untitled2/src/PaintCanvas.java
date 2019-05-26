import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.io.PrintWriter;


import static javax.swing.UIManager.get;


public class PaintCanvas extends JPanel {
    private ArrayList<Shapes> entries = new ArrayList();
    private mouseHandler mouse = new mouseHandler();
    private actionHandler acts = new actionHandler();

    private static final Color BG = Color.WHITE;
    int x1, x2, y1, y2;
    private Color colour = new Color(0, 0, 0);
    private int shape = 0;
    private Color colourLine = Color.BLACK;
    private Color colourFill = Color.WHITE;
    private boolean fill;

    private JPanel pnlBtns = new JPanel();
    private JPanel pnlCanvas = new JPanel();
    private JButton btnPlot = new JButton("PLOT");
    private JButton btnLine = new JButton("LINE");
    private JButton btnRect = new JButton("RECTANGLE");
    private JButton btnOval = new JButton("OVAL");
    private JButton btnCol = new JButton("  ");
    private JButton btnColF = new JButton("  ");
    private JCheckBox btnFill = new JCheckBox("Fill");
    private JButton btnSave = new JButton("SAVE");

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
        this.btnCol.setBackground(Color.BLACK);
        this.btnColF.setBackground(Color.WHITE);

        this.add(this.pnlBtns, "North");
        this.add(this.pnlCanvas, "Center");
        this.addMouseListener(this.mouse);
        nullBtns();
        btnPlot.setBackground(Color.CYAN);
    }

    public float encodeX(int width) { //encode pixel location to percentage of total screen width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        float decimal = (width/pixels);
        return decimal;
    }

    public float encodeY(int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        float decimal = (height/pixels);
        return decimal;
    }

    public int decodeX(float width) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        int location = (int)Math.round(width*pixels);
        return location;
    }

    public int decodeY(float height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        float pixels = (float)Math.round(screenSize.height * 0.85);
        int location = (int)Math.round(height*pixels);
        return location;
    }



    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (!this.entries.isEmpty()) {
            for (int i = 0; i < this.entries.size(); ++i) {
                g.setColor((this.entries.get(i)).colourLine);
                if ((this.entries.get(i)).colourLine != g.getColor()) {
                    g.setColor((this.entries.get(i)).colourLine);
                }

                if ((this.entries.get(i)).type == 0) {// if entry i is PLOT type
                    g.setColor((this.entries.get(i)).colourLine);
                    g.fillRect(decodeX(entries.get(i).x[0])-2, decodeY(entries.get(i).y[0])-2,4,4);// Draw Filled Square 3*3 pixels wide centered on mouse pointer
                }
                else if ((this.entries.get(i)).type == 1) {// if entry i is LINE type
                    g.setColor((this.entries.get(i)).colourLine);
                    g.drawLine(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1]), decodeY(entries.get(i).y[1]));
                }
                else if ((this.entries.get(i)).type == 2) {
                    if (!entries.get(i).fill) {
                        g.setColor((this.entries.get(i)).colourLine);
                        g.drawRect(decodeX(entries.get(i).x[0] + 1), decodeY(entries.get(i).y[0] + 1), decodeX(entries.get(i).x[1] - entries.get(i).x[0]) - 2, decodeY(entries.get(i).y[1] - entries.get(i).y[0]) - 2);
                    } else {
                        g.setColor(this.entries.get(i).colourLine);
                        g.drawRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                        g.setColor(this.entries.get(i).colourFill);
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
                    if (entries.get(i).fill) {
                        g.setColor((this.entries.get(i)).colourLine);
                        g.fillOval(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                    } else {
                        g.drawOval(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                    }
                }
            }
        }
    }

    /*private class MotionHandler implements MouseMotionListener {
        private MotionHandler() {
        }

        public void mouseMoved(MouseEvent mE) {
        }
        public void mouseDragged(MouseEvent mE) {
        }
    }*/
    private class mouseHandler implements MouseListener {
        private mouseHandler() {

        }

        public void mouseEntered(MouseEvent me) {
        }

        public void mouseExited(MouseEvent me) {
        }

        public void mousePressed(MouseEvent me) {
            x1 = me.getX();
            y1 = me.getY();
            repaint();
            //System.out.println(x1);
            //System.out.println(y1);

        }

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
                    entries.add(new Plot(encodeX(x2), encodeY(y2)));
                    System.out.println(entries.size());
                    repaint();
                    break;
                case 1:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Line(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2)));
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
    private void nullBtns() {
        btnPlot.setBackground((Color)null);
        btnLine.setBackground((Color)null);
        btnRect.setBackground((Color)null);
        btnOval.setBackground((Color)null);
    }

    private class actionHandler implements ActionListener {
        private actionHandler() {
        }

        public void actionPerformed(ActionEvent a) {
            if (a.getSource()== btnPlot){
                shape = 0;
                nullBtns();
                btnPlot.setBackground(Color.CYAN);
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
                Color c = JColorChooser.showDialog(null,"Primary Colour",Color.BLACK,false);
                btnCol.setBackground(c);
                entries.add(new ColorFill(c));
                colourLine = c;
            }
            if (a.getSource() == btnColF) {
                Color c2 = JColorChooser.showDialog(null,"Secondary Colour",Color.WHITE,false);
                btnColF.setBackground(c2);
                entries.add(new ColorFill(c2));
                colourFill = c2;
            }

            if (a.getSource() == btnSave) { //save code
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
                                String line = String.format("PLOT %6f %6f",entries.get(i).x[0],entries.get(i).y[0]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 1) {
                                String line = String.format("LINE %6f %6f %6f %6f",entries.get(i).x[0],entries.get(i).y[0],entries.get(i).x[1],entries.get(i).y[1]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 2) {
                                String line = String.format("RECTANGLE %6f %6f %6f %6f",entries.get(i).x[0],entries.get(i).y[0],entries.get(i).x[1],entries.get(i).y[1]);
                                out.println(line);
                            }
                            if (entries.get(i).type == 3) {
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

