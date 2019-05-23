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
    private final int PLOT = 0, LINE = 1, SQUARE = 2, OVAL = 3, POLYGON = 4;
    int x1, x2, y1, y2;
    private Color colour = new Color(0, 0, 0);
    private int selectedShape;
    private int shape = 0;

    private JPanel pnlBtns = new JPanel();
    private JPanel pnlCanvas = new JPanel();
    private JButton btnPlot = new JButton("PLOT");
    private JButton btnLine = new JButton("LINE");
    private JButton btnRect = new JButton("RECTANGLE");
    private JButton btnOval = new JButton("OVAL");
    private JButton btnCol = new JButton("  ");

    public PaintCanvas() {
        this.btnPlot.addActionListener(acts);
        this.btnLine.addActionListener(acts);
        this.btnRect.addActionListener(acts);
        this.btnOval.addActionListener(acts);
        this.pnlBtns.add(this.btnPlot);
        this.pnlBtns.add(this.btnLine);
        this.pnlBtns.add(this.btnRect);
        this.pnlBtns.add(this.btnOval);
        this.pnlBtns.add(this.btnCol);
        this.btnCol.setBackground(this.colour);

        this.add(this.pnlBtns, "North");
        this.add(this.pnlCanvas, "Center");
        this.addMouseListener(this.mouse);
        nullBtns();
        btnPlot.setBackground(Color.CYAN);
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        if (this.entries.isEmpty() == false) {
            for (int i = 0; i < this.entries.size(); ++i) {
                if ((this.entries.get(i)).type == 0) {// if entry i is PLOT type
                    g.fillRect(entries.get(i).x[0]-2,entries.get(i).y[0]-2,4,4 );// Draw Filled Square 4*4 pixels wide centered on mouse pointer
                }
                else if ((this.entries.get(i)).type == 1) {// if entry i is LINE type
                    g.drawLine(entries.get(i).x[0], entries.get(i).y[0], entries.get(i).x[1], entries.get(i).y[1]);
                }
                else if ((this.entries.get(i)).type == 2) {
                    g.fillRect(entries.get(i).x[0], entries.get(i).y[0], entries.get(i).x[1]-entries.get(i).x[0] , entries.get(i).y[1]- entries.get(i).y[0]);
                }
                else if ((this.entries.get(i)).type == 3) {
                    g.fillOval(entries.get(i).x[0], entries.get(i).y[0], entries.get(i).x[1]-entries.get(i).x[0] , entries.get(i).y[1]- entries.get(i).y[0]);
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
            switch (PaintCanvas.this.shape) {
                case 0:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Plot(x2, y2, colour));
                    System.out.println(entries.size());
                    repaint();
                    break;
                case 1:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Line(x1, y1, x2, y2, colour));
                    repaint();
                    break;
                case 2:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Rectangle(x1, y1, x2, y2, colour));
                    repaint();
                    break;
                case 3:
                    x2 = me.getX();
                    y2 = me.getY();
                    entries.add(new Oval(x1, y1, x2, y2, colour));
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
        }
    }
}

