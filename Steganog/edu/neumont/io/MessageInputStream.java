package edu.neumont.io;

import java.io.IOException;

public interface MessageInputStream extends AutoCloseable {
	Message readMessage() throws IOException;
	void close() throws IOException;
}
