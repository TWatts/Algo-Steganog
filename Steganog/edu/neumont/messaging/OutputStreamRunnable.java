package edu.neumont.messaging;
import java.io.OutputStream;
import java.io.PrintWriter;


public class OutputStreamRunnable implements Runnable {
	private OutputStream os;
	private String recipient;
	
	public OutputStreamRunnable(OutputStream os, String recipient) {
		this.os = os;
		this.recipient = recipient;
	}
	
	@Override
	public void run() {
		MessageQueue mq = MessageBroker.getInstance().getMessageQueue(recipient);
		try ( PrintWriter pw = new PrintWriter(os, true) ) {
			String message = mq.poll();
			while ( message != null ) {
				pw.println(message);
				message = mq.poll();
			}
		}
	}

}
