package edu.neumont.messaging;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class ClientOutputStreamRunnable implements Runnable {
	private OutputStream os;
	private String sender;
	
	public ClientOutputStreamRunnable(OutputStream os, String sender) {
		this.os = os;
		this.sender = sender;
	}
	
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		try ( PrintWriter pw = new PrintWriter(os, true) ) {
			pw.println(sender);
			while ( true ) {
				System.out.println("What message to you want to send (format:  [sender:message])?");
				String message = scanner.nextLine();
				pw.println(message);
			}
		}
	}

}
