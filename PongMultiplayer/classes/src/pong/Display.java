package pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display extends JPanel {

	private static final long serialVersionUID = 1L;
	int PANEL_WIDTH = 584;
	int PANEL_HEIGHT = 362;
	private int BALL_X;
	private int BALL_Y;
	private int BALL_SIZE;
	private int BALL_X_VELOCITY;
	private int BALL_Y_VELOCITY;
	private int PLAYER_WIDTH;
	private int PLAYER_HEIGHT;
	private int SELF_X;
	private int SELF_Y;
	private int OP_X;
	private int OP_Y;
	int player;
	boolean gameRunning = true;
	Handler handler;
	Checks checks;

	Display() {
		handler = Instances.getHandler();
		checks = Instances.getChecks();
	}
	
	public void getConcreteValues() {
		
		BALL_SIZE = handler.getBallSize();
		PLAYER_WIDTH = handler.getPlayerWidth();
		PLAYER_HEIGHT = handler.getPlayerHeight();
		SELF_X = handler.getSelfX();
		OP_X = handler.getOpX();
		addMouseMotionListener(new Controller(player = handler.getPlayer()));
		
		checks.setConcreteValues(PANEL_WIDTH, PANEL_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT, SELF_X, OP_X, BALL_SIZE, player);
		
	}
	
	public void getVariableValues() {
		
		int[] revievedData = handler.getData();
		BALL_X = revievedData[0];
		BALL_Y = revievedData[1];
		BALL_X_VELOCITY = revievedData[2];
		BALL_Y_VELOCITY = revievedData[3];
		SELF_Y = revievedData[4];
		OP_Y = revievedData[5];
		
		repaint();
		
		checks.performChecks(BALL_X, BALL_Y, BALL_X_VELOCITY, BALL_Y_VELOCITY, SELF_Y, OP_Y);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g.setColor(Color.WHITE);
		g.fillOval(BALL_X, BALL_Y, BALL_SIZE, BALL_SIZE);
		g.fillRect(SELF_X, SELF_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
		g.fillRect(OP_X, OP_Y, PLAYER_WIDTH, PLAYER_HEIGHT);
		
	}
	
}
