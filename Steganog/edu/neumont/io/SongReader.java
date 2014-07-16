package edu.neumont.io;

import java.io.IOException;

public abstract class SongReader {
	public String readSong() throws IOException {
		try ( MessageInputStream mis = getMessageInputStream("song.out") ) {
			Message m = mis.readMessage();
			String song = getSong(m);
			return song;
		}
	}
	
	/**
	 * Get an instance of a MessageInputStream that will read the message
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	protected abstract MessageInputStream getMessageInputStream(String fileName) throws IOException;
	
	/**
	 * Read the song from the message.  
	 * @param m
	 * @return
	 * @throws IOException - If the message could not be read
	 */
	protected abstract String getSong(Message m) throws IOException;
}
