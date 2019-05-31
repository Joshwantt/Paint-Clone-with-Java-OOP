import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class extends the JFrame library in order to create a pop-out GUI window for selecting colours.
 */
public class ColourChooser extends JFrame {
    private JLabel label = new JLabel("Label");
    private JButton chooseBtn = new JButton("Choose Color");

    /**
     * Constructs a 300*100 window
     */
    public ColourChooser() {
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1 = new JPanel();
        label.setBackground(null);
        panel1.add(label);

        chooseBtn.addActionListener(new ButtonListener());
        panel1.add(chooseBtn);

        this.add(panel1);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color c = JColorChooser.showDialog(null, "Choose a Color", label.getForeground());
            if (c != null)
                label.setForeground(c);


        }
    }
}
