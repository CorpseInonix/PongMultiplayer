package pong;

import java.awt.event.*;
import javax.swing.*;

public class Gameloop implements ActionListener {
	
	Timer timer;
	Display display;
	Handler handler;
	
	Gameloop() {
		
		display = Instances.getDisplay();
		handler = Instances.getHandler();
				
		timer = new Timer(20, this);
		timer.addActionListener(this);
		timer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		display.getVariableValues();
		handler.sendDataBack();
	}

}
