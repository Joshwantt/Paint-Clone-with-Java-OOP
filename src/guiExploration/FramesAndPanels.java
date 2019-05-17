/**
 * 
 */
package guiExploration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author hogan
 *
 */
public class FramesAndPanels extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	

	private JPanel pnlOne;
	private JPanel pnlTwo;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel pnlFive;
		
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public FramesAndPanels(String arg0) throws HeadlessException {
		super(arg0);
	}
	
	private void createGUI() {
		setSize(WIDTH, HEIGHT);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    //Solution code uses different colours to highlight different panels 
	    pnlOne = createPanel(Color.BLUE);
	    pnlTwo = createPanel(Color.CYAN);
	    pnlThree = createPanel(Color.DARK_GRAY);
	    pnlFour = createPanel(Color.RED);
	    pnlFive = createPanel(Color.GREEN);
	        
	    this.getContentPane().add(pnlOne,BorderLayout.CENTER);
	    this.getContentPane().add(pnlTwo,BorderLayout.NORTH);
	    this.getContentPane().add(pnlThree,BorderLayout.SOUTH);
	    this.getContentPane().add(pnlFour,BorderLayout.EAST);
	    this.getContentPane().add(pnlFive,BorderLayout.WEST);
	    repaint(); 
	    this.setVisible(true);
	}
	
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
		
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI(); 
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new FramesAndPanels("BorderLayout"));
	}
}
