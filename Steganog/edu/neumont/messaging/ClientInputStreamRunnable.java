package edu.neumont.messaging;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientInputStreamRunnable implements Runnable {
	private InputStream is;
	
	public ClientInputStreamRunnable(InputStream inputStream) {
		this.is = inputStream;
	}
	
	@Override
	public void run() {
		try ( BufferedReader br = new BufferedReader(new InputStreamReader(is)) ) {
			String line = br.readLine();
			while ( line != null ) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

}
