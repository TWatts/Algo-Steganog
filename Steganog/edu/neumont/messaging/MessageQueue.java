package edu.neumont.messaging;
import java.util.LinkedList;
import java.util.Queue;


public class MessageQueue {
	private Queue<String> messages = new LinkedList<String>();
	private String recipient;
	
	public MessageQueue(String recipient) {
		this.recipient = recipient;
	}

	public synchronized void offer(String sender, String message) {
		while ( !messages.isEmpty() ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		messages.offer(sender + ": " + message);
		notifyAll();
	}
	
	public synchronized String poll() {
		while ( messages.isEmpty() ) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String message = messages.poll();
		notifyAll();
		return message;
	}
}
