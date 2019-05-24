import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static javax.swing.UIManager.get;


public class PaintCanvas extends JPanel {
    private ArrayList<Shapes> entries = new ArrayList();
    private mouseHandler mouse = new mouseHandler();
    private actionHandler acts = new actionHandler();

    private static final Color BG = Color.WHITE;
    int x1, x2, y1, y2;
    private Color colour = new Color(0, 0, 0);
    private int shape = 0;
    private int Colorfill = 0;
    private boolean fill;

    private JPanel pnlBtns = new JPanel();
    private JPanel pnlCanvas = new JPanel();
    private JButton btnPlot = new JButton("PLOT");
    private JButton btnLine = new JButton("LINE");
    private JButton btnRect = new JButton("RECTANGLE");
    private JButton btnOval = new JButton("OVAL");
    private JButton btnCol = new JButton("  ");
    private JCheckBox btnFill = new JCheckBox("Fill");

    public PaintCanvas() {
        this.btnPlot.addActionListener(acts);
        this.btnLine.addActionListener(acts);
        this.btnRect.addActionListener(acts);
        this.btnOval.addActionListener(acts);
        this.btnFill.addActionListener(acts);
        this.btnCol.addActionListener(acts);
        this.pnlBtns.add(this.btnPlot);
        this.pnlBtns.add(this.btnLine);
        this.pnlBtns.add(this.btnRect);
        this.pnlBtns.add(this.btnOval);
        this.pnlBtns.add(this.btnCol);
        this.pnlBtns.add(this.btnFill);
        this.btnCol.setBackground(this.colour);

        this.add(this.pnlBtns, "North");
        this.add(this.pnlCanvas, "Center");
        this.addMouseListener(this.mouse);
        nullBtns();
        btnPlot.setBackground(Color.CYAN);
    }

    public double encodeX(int width) { //encode pixel location to percentage of total screen width
        int pixels = pnlCanvas.getWidth();
        double decimal = width/pixels;
        return decimal;
    }

    public double encodeY(int height) {
        int pixels = pnlCanvas.getHeight();
        double decimal = height/pixels;
        return decimal;
    }

    public int decodeX(double width) {
        int pixels = pnlCanvas.getWidth();
        int location = (int)Math.round(width*pixels);
        return location;
    }

    public int decodeY(double height) {
        int pixels = pnlCanvas.getHeight();
        int location = (int)Math.round(height*pixels);
        return location;
    }



    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (!this.entries.isEmpty()) {
            for (int i = 0; i < this.entries.size(); ++i) {
                if ((this.entries.get(i)).colourFill != g.getColor()) {
                    g.setColor((this.entries.get(i)).colourFill);
                }

                if ((this.entries.get(i)).type == 0) {// if entry i is PLOT type
                    g.fillRect(decodeX(entries.get(i).x[0])-2, decodeY(entries.get(i).y[0])-2,4,4);// Draw Filled Square 3*3 pixels wide centered on mouse pointer
                }
                else if ((this.entries.get(i)).type == 1) {// if entry i is LINE type
                    g.drawLine(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1]), decodeY(entries.get(i).y[1]));
                }
                else if ((this.entries.get(i)).type == 2) {
                    if (entries.get(i).fill) {
                        g.fillRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                        if (this.entries.get(i).colourFill != this.entries.get(i).colourLine) {
                            Color save = g.getColor();
                            g.setColor(this.entries.get(i).colourLine);
                            g.drawRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                            g.setColor(save);
                        }
                    } else {
                        g.drawRect(decodeX(entries.get(i).x[0]), decodeY(entries.get(i).y[0]), decodeX(entries.get(i).x[1] - entries.get(i).x[0]), decodeY(entries.get(i).y[1] - entries.get(i).y[0]));
                    }
                }
                else if ((this.entries.get(i)).type == 3) {
                    if (entries.get(i).fill) {
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
                    entries.add(new Rectangle(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2),fill));
                    repaint();
                    break;
                case 3:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Oval(encodeX(x1), encodeY(y1), encodeX(x2), encodeY(y2),fill));
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
            if (a.getSource() == btnCol && Colorfill != 1) {
                entries.add(new ColorFill(Color.BLUE));
                entries.add(new ColorLine(Color.BLACK));
                Colorfill = 1;
            } else {
                entries.add(new ColorFill(Color.BLACK));
                entries.add(new ColorLine(Color.BLUE));
                Colorfill = 0;
            }
        }
    }
}

