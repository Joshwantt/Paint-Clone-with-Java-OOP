//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
import javax.swing.JFrame;

/**
 * The Main GUI Frame of the program
 * Creates itself then constructs a PaintCanvas for drawing.
 */
public class PaintFrame extends JFrame {
    public PaintFrame() {
        PaintCanvas panel = new PaintCanvas();
        this.setContentPane(panel);
        this.setSize(1280,1280);
        this.setVisible(true);
        this.setResizable(true);
        this.setDefaultCloseOperation(3);
    }
}
