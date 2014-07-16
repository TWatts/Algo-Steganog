package edu.neumont.io;

import java.io.IOException;

public abstract class SongWriter {
	public final void writeSong(String song) throws IOException {
		MessageOutputStream os = getMessageOutputStream("song.out");
		os.writeMessage(createMessage(song));
	}
	
	/**
	 * Get an instance of the Message to write
	 * @param message
	 * @return
	 * @throws IOException
	 */
	public abstract Message createMessage(String message) throws IOException;
	
	/**
	 * Get an instance of MessageOutputStream for writing a message.
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public abstract MessageOutputStream getMessageOutputStream(String fileName) throws IOException;
	
	public final String getFileName() {
		return "song.out";
	}
}
