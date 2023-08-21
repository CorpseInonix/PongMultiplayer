package pong;

import java.io.*;
import java.net.*;

public class Handler implements Runnable {
	
	int BALL_X;
	int BALL_Y;
	int BALL_SIZE;
	int BALL_X_VELOCITY;
	int BALL_Y_VELOCITY;
	int PLAYER_WIDTH;
	int PLAYER_HEIGHT;
	int SELF_X;
	int SELF_Y;
	int OP_X;
	int OP_Y;
	int player;
	
	PrintWriter writer;
	BufferedReader reader;
	Thread thread;
	
	public void setUpConnection(String SERVER_IP, int PORT) {
		
		try {
			
			Socket socket = new Socket(SERVER_IP, PORT);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			thread = new Thread(this);
			thread.start();
			
		} catch (Exception ex) {
			
			System.out.println("Failed, try again later");
			ex.printStackTrace();
			
		}
		
	}
	
	public String formMessage(int x, int y, int selfY, int opY) {
		
		String a = Integer.toString(x);
		String b = Integer.toString(y);
		String c = Integer.toString(selfY);
		String d = Integer.toString(opY);
		
		String message = a + "/" + b + "/" + c + "/" + d;
		
		return message;
		
	}
	
	public void sendDataBack() {
		
		String data = "";
		data = Integer.valueOf(SELF_Y) + "/" + Integer.valueOf(OP_Y);
		
		if(BALL_X > 578 || BALL_X < 1) 
			data+="/fuck";

		writer.println(data);
		writer.flush();
	}
	
	@Override
	public void run() {
		
		String MSG = "";
		
		try {

			while((MSG = reader.readLine()) != null) {

				String[] tokens = MSG.split("/");
				
				if(tokens.length == 2) {

				    SELF_Y = Integer.valueOf(tokens[0]);
				    OP_Y = Integer.valueOf(tokens[1]);
					
				} else if(tokens.length == 10) {
					
					BALL_X = Integer.valueOf(tokens[0]);
				    BALL_Y = Integer.valueOf(tokens[1]);
					BALL_SIZE = Integer.valueOf(tokens[2]);
				    BALL_X_VELOCITY = Integer.valueOf(tokens[3]);
				    BALL_Y_VELOCITY = Integer.valueOf(tokens[4]);
				    PLAYER_WIDTH = Integer.valueOf(tokens[5]);
				    PLAYER_HEIGHT =Integer.valueOf(tokens[6]);
				    player = Integer.valueOf(tokens[9]);
				    
				    if(player == 0) {
					    SELF_X = Integer.valueOf(tokens[7]);
					    OP_X = Integer.valueOf(tokens[8]);
				    } else {
					    OP_X = Integer.valueOf(tokens[7]);
					    SELF_X = Integer.valueOf(tokens[8]);
				    }
				  
				} else { }
				
			}
			
		} catch (Exception ex) { ex.printStackTrace(); }
		
	}

	//Methods below send the data to other classes for calculation
	
	public int[] getData() {
		
		BALL_X+=BALL_X_VELOCITY;
		BALL_Y+=BALL_Y_VELOCITY;
		int[] data = {BALL_X, BALL_Y, BALL_X_VELOCITY, BALL_Y_VELOCITY, SELF_Y, OP_Y};
		
		return data;
		
	}
	
	public int getSelfX() {
		return SELF_X;
	}
	
	public int getOpX() {
		return OP_X;
	}
	
	public int getBallSize() {
		return BALL_SIZE;
	}
	
	public int getPlayerWidth() {
		return PLAYER_WIDTH;
	}
	
	public int getPlayerHeight() {
		return PLAYER_HEIGHT;
	}
	
	public int getPlayer() {
		return player;
	}
	
	//Methods below are for setting important values
	
	public void setBallX(int x) {
		BALL_X = x;
	}
	
	public void setBallY(int y) {
		BALL_Y = y;
	}
	
	public void setBallXvelocity(int velocity) {
		BALL_X_VELOCITY = velocity;
	}
	
	public void setBallYvelocity(int velocity) {
		BALL_Y_VELOCITY = velocity;
	}
	
	public void setSelfY(int position) {
		SELF_Y = position;
	}
	
	public void setOpY(int position) {
		OP_Y = position;
	}

}
