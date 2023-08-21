package pong;

public class Checks {
	
	int PANEL_WIDTH;    
	int PANEL_HEIGHT;
	int PLAYER_WIDTH;
	int PLAYER_HEIGHT;
	int BALL_X;
	int BALL_Y;
	int BALL_X_VELOCITY;
	int BALL_Y_VELOCITY;
	int BALL_SIZE;
	int SELF_X;
	int SELF_Y;
	int OP_X;
	int OP_Y;
	int JUST_A_VALUE;
	int player;
	int opponent;
	
	Handler handler;
	
	Checks() {
		if(handler == null)
			handler = Instances.getHandler();
	}
	
	public void performChecks(int ballX, int ballY, int ballXvelocity, int ballYvelocity, int selfY, int opY) {
		
		BALL_X = ballX;
		BALL_Y = ballY;
		BALL_X_VELOCITY = ballXvelocity;
		BALL_Y_VELOCITY = ballYvelocity;
		SELF_Y = selfY;
		OP_Y = opY;
		
		if((SELF_Y + PLAYER_HEIGHT) > PANEL_HEIGHT) 
			SELF_Y = PANEL_HEIGHT - PLAYER_HEIGHT;
		
		if((OP_Y + PLAYER_HEIGHT) > PANEL_HEIGHT) 
			OP_Y = PANEL_HEIGHT - PLAYER_HEIGHT;
		
		if(BALL_Y > (PANEL_HEIGHT - BALL_SIZE) || BALL_Y < 1) 
			BALL_Y_VELOCITY = BALL_Y_VELOCITY * -1;
		
		if(BALL_Y > SELF_Y && BALL_Y < (SELF_Y + PLAYER_HEIGHT)) 
			if(BALL_X < (SELF_X + PLAYER_WIDTH))
				BALL_X_VELOCITY = BALL_X_VELOCITY * -1;
		
		if(BALL_Y > OP_Y && BALL_Y < (OP_Y + PLAYER_HEIGHT)) 
			if(BALL_X > (OP_X - (PLAYER_WIDTH * 2)))
				BALL_X_VELOCITY = BALL_X_VELOCITY * -1;
		
		setValues();
		
	}
	
	public void setValues() {
		handler.setBallXvelocity(BALL_X_VELOCITY);
		handler.setBallYvelocity(BALL_Y_VELOCITY);
		handler.setSelfY(SELF_Y);
		handler.setOpY(OP_Y);
	}
	
	public void setConcreteValues(int panelWidth, int panelHeight, int playerWidth, int playerHeight, int selfX, int opX, int ballSize, int user) {
		PANEL_WIDTH = panelWidth;
		PANEL_HEIGHT = panelHeight;
		PLAYER_WIDTH = playerWidth;
		PLAYER_HEIGHT = playerHeight;
		SELF_X = selfX;
		OP_X = opX;
		BALL_SIZE = ballSize;
		player = user;
		if(player == 0) 
			opponent = 1;
		else
			opponent = 0;
	}
	
	public void reposition() {
		handler.setBallX(280);
		handler.setBallY(160);
	}

}
