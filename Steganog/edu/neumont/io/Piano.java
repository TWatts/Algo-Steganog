package edu.neumont.io;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Piano {
	private JFrame frame;
	private SongWriter sw;
	
	public Piano(SongWriter sw) {
		this.sw = sw;
	}
	
	public void show() {
		if ( frame == null ) {
			frame = new JFrame();
			frame.setTitle("Piano");
			frame.setLayout(new BorderLayout());
			
			final JTextField songField = new JTextField(80);
			frame.add(songField, BorderLayout.CENTER);
			
			JButton go = new JButton("Go");
			go.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						sw.writeSong(songField.getText());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, e1.getMessage());
					}
				}
			});
			frame.add(go, BorderLayout.WEST);
			
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);			
		}
		
		frame.repaint();
	}
}
