package pong;

import java.util.*;

public class Main {
	
	Scanner sc;
	String ip;
	int port;

	public static void main(String args[]) {
		Instances.initialize();
		new Main().start();
	}
	
	public void start() {
		Handler handler = Instances.getHandler();
		Display display = Instances.getDisplay();
		new GUI();
		getMaterials();
		handler.setUpConnection(ip, port);
		
		try {
			Thread.sleep(15);
		} catch (Exception ex) { }
		
		display.getConcreteValues();
		display.getVariableValues();
		new Gameloop();
	}
	
	public void getMaterials() {
		
		sc = new Scanner(System.in);
		System.out.println("Enter the server ip");
		ip = sc.nextLine();
		System.out.println("Enter the server port");
		port = sc.nextInt();
		
	}

}
