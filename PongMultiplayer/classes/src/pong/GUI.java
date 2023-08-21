package pong;

import javax.swing.*;

public class GUI extends JFrame {

	GUI() {
		
		Display display = Instances.getDisplay();
		
		setTitle("Pong");
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(3);
		add(display);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

}
