package pong;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

	int BALL_X = 280;
	int BALL_Y = 160;
	int BALL_SIZE = 12;
	int BALL_X_VELOCITY = 4;
	int BALL_Y_VELOCITY = 4;
	int PLAYER_WIDTH = 8;
	int PLAYER_HEIGHT = 60;
	int PLAYER_A_X = 0;
	int PLAYER_A_Y;
	int PLAYER_B_X = 576;
	int PLAYER_B_Y;
	int players;

	ServerSocket SERVER_SOCKET;
	ArrayList<PrintWriter> clients;

	public static void main(String args[]) {
		new Server();
	}

	Server() {

		clients = new ArrayList<PrintWriter>();

		try {

			SERVER_SOCKET = new ServerSocket(1000);
			System.out.println("Server running...");

			while (true) {

				Socket sock = SERVER_SOCKET.accept();
				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				clients.add(writer);
				sendDefaultValues();
				
				Thread t = new Thread(new ClientHandler(sock));
				t.start();
				
				if(players > 1) 
					break;

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void sendDefaultValues() {
		
		if(players == 0) {
			PLAYER_A_X = 0;
			PLAYER_B_X = 576;
		} else if(players == 1) {
			PLAYER_A_X = 576;
			PLAYER_B_X = 0;
		}

		String ballX = String.valueOf(BALL_X);
		String ballY = String.valueOf(BALL_Y);
		String ballSize = String.valueOf(BALL_SIZE);
		String ballXVelocity = String.valueOf(BALL_X_VELOCITY);
		String ballYVelocity = String.valueOf(BALL_Y_VELOCITY);
		String playerWidth = String.valueOf(PLAYER_WIDTH);
		String playerHeight = String.valueOf(PLAYER_HEIGHT);
		String selfX = String.valueOf(PLAYER_A_X);
		String opX = String.valueOf(PLAYER_B_X);
		String users = String.valueOf(players);

		String msg = ballX + "/" + ballY + "/" + ballSize + "/" + ballXVelocity + "/" + ballYVelocity + "/" + playerWidth + "/" + playerHeight + "/"
				+ selfX + "/" + opX + "/" + users;

		for (PrintWriter writer : clients) {
			writer.println(msg);
			writer.flush();
		}
		
		players++;

	}

	public class ClientHandler implements Runnable {

		BufferedReader reader;

		ClientHandler(Socket sock) {
			try {
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			} catch (Exception ex) { }
		}

		public void sendToAll(String s) {
			Iterator it = clients.iterator();
			while (it.hasNext()) {
				
				try {
					
					PrintWriter writer = (PrintWriter) it.next();
					writer.println(s);
					writer.flush();
					
				} catch (Exception ex) { }

			}
			
		}
		
		public void alterValues(String msg) {

			String tokens[] = msg.split("/");
			if(tokens.length == 2) {
				PLAYER_A_Y = Integer.valueOf(tokens[0]);
				PLAYER_B_Y = Integer.valueOf(tokens[1]);
			} else {
				sendDefaultValues();
			}
			
		}

		@Override
		public void run() {

			String msg;
			try {
				while ((msg = reader.readLine()) != null) {
					alterValues(msg);
					sendToAll(msg);
				}
			} catch (Exception ex) { }

		}

	}

}
