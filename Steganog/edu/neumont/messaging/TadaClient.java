package edu.neumont.messaging;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.jfugue.Player;


public class TadaClient {
	private static final Player player = new Player();
	private static final Set<String> firstTimeSender = new HashSet<String>();
	
	public static void main(String[] args) {
		try ( Socket socket = new Socket("localhost", 31415) ) {
			OutputStream os  = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("jzheaux");
			try ( BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())) ) {
				String line = br.readLine();
				while ( line != null ) {
					System.out.println(line);
					Scanner scan = new Scanner(line).useDelimiter(":");
					String sender = scan.next();
					if ( firstTimeSender.add(sender) ) {
						player.play("I58 C6s+E6s+G6s+C7s C6w+E6w+G6w+C7w");
					}
					line = br.readLine();
				}
			}
		} catch ( IOException e ) {
			System.out.println("Couldn't open socket!");
		}
	}
}
