package edu.neumont.io;

import java.io.IOException;

public class Headphones {
	Player player = new Player();
	
	public Headphones(final SongReader sr) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						String song = sr.readSong();
						player.play(song);
						// play song
					} catch (IOException e1) {
						System.out.println("Didn't find message, will poll again in 1 second");
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
		});
		t.start();
	}
}