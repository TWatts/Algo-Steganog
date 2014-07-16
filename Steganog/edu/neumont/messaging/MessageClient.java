package edu.neumont.messaging;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MessageClient {	
	public static void main(String... args) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost", 31416);
		new Thread(new ClientOutputStreamRunnable(s.getOutputStream(), args[0])).start();
		new Thread(new ClientInputStreamRunnable(s.getInputStream())).start();
	}
}
