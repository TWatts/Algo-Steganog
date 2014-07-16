package edu.neumont.messaging;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(31415);
		Socket s = ss.accept();
		try ( BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())) ) {
			String line = br.readLine();
			if ( "jzheaux".equals(line) ) {
				new Thread(new OutputStreamRunnable(s.getOutputStream(), line)).start();
				ServerSocket ss2 = new ServerSocket(31416);
				while ( true ) {
					Socket s2 = ss2.accept();
					BufferedReader br2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
					String line2 = br2.readLine();
					System.out.println("Received: " + line2);
					new Thread(new InputStreamRunnable(s2.getInputStream(), line2)).start();
					new Thread(new OutputStreamRunnable(s2.getOutputStream(), line2)).start();					
				}
			}
		}
	}
}
