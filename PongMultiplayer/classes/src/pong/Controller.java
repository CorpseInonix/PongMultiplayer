package pong;

import java.awt.event.*;

public class Controller implements MouseMotionListener {
	
	Handler handler = Instances.getHandler();
	int player;
	
	Controller(int x) { 
		player = x;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(player == 0) 
			handler.setSelfY(e.getY());
		else
			handler.setOpY(e.getY());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

}
