package edu.neumont.io;

import java.io.IOException;

public interface MessageOutputStream {
	public void writeMessage(Message m) throws IOException;
}
