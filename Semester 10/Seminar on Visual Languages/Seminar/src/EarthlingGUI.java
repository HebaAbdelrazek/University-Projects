import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EarthlingGUI {

	

	
	public static void main(String args[]){
	       JFrame frame = new JFrame("My First GUI");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setSize(1200,800);
	       
	       
	       
	       JPanel panel = new JPanel();
	       JLabel label = new JLabel("HELLO EARTHLING!");
	       
	       panel.add(label);
	       frame.getContentPane().add(BorderLayout.NORTH, panel);
	       
//	       JButton button = new JButton("Press");
//	       frame.getContentPane().add(button); // Adds Button to content pane of frame
	       frame.setVisible(true);
	    }	

}
