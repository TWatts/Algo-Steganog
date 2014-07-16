package edu.neumont.messaging;
import java.util.HashMap;
import java.util.Map;


public class MessageBroker {
	private static final MessageBroker mb = new MessageBroker();
	
	private final Map<String, MessageQueue> messagesByUser = new HashMap<String, MessageQueue>();
	
	public static MessageBroker getInstance() {
		return mb;
	}
	
	public synchronized MessageQueue getMessageQueue(String recipient) {
		MessageQueue mq = messagesByUser.get(recipient);
		if ( mq == null ) {
			mq = new MessageQueue(recipient);
			messagesByUser.put(recipient, mq);
		}
		return mq;
	}
}
