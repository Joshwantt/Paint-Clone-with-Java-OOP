//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Toolkit;
import javax.swing.JFrame;

public class PaintFrame extends JFrame {
    public PaintFrame() {
        PaintCanvas panel = new PaintCanvas();
        this.setContentPane(panel);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
    }
}
