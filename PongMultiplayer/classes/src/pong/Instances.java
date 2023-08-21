package pong;

public class Instances {
	
	static Display display;
	static Handler handler;
	static Checks checks;
	
	public static void initialize() {
		
		handler = new Handler();
		checks = new Checks();
		display = new Display();
		
	}
	
	public static Display getDisplay() {
		return display;
	}
	
	public static Handler getHandler() {
		return handler;
	}
	
	public static Checks getChecks() {
		return checks;
	}

}
