//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class PaintFrame extends JFrame {
    public PaintFrame() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int size = (int)Math.round(screenSize.height * 0.85);

        PaintCanvas panel = new PaintCanvas();
        this.setContentPane(panel);
        this.setSize(size,size);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(3);
    }
}
