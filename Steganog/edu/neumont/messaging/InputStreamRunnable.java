package edu.neumont.messaging;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class InputStreamRunnable implements Runnable {
	private InputStream is;
	private String sender;
	
	public InputStreamRunnable(InputStream inputStream, String sender) {
		this.is = inputStream;
		this.sender = sender;
	}
	
	@Override
	public void run() {
		try ( BufferedReader reader = new BufferedReader(new InputStreamReader(is)) ) {
			String line = reader.readLine();
			while ( line != null ) {
				Scanner scan = new Scanner(line).useDelimiter(":");
				String recipient = scan.next(), message = scan.next();
				MessageQueue mq = MessageBroker.getInstance().getMessageQueue(recipient);
				mq.offer(sender, message);
				
				mq = MessageBroker.getInstance().getMessageQueue("jzheaux");
				mq.offer(sender, message);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
