package edu.neumont.sql;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Querier implements ActionListener, KeyListener {
	private JFrame frame;
	private JTextField query;
	private DataSource dataSource;
	
	public Querier(DataSource source) {
		dataSource = source;
	}
	
	public void show() {
		if ( frame == null ) {
            frame = new JFrame();

            JButton go = new JButton("Run Query");
            go.addActionListener(this);
            query = new JTextField(40);
            query.addKeyListener(this);

            JPanel queryPanel = new JPanel(new FlowLayout());
            queryPanel.add(go);
            queryPanel.add(query);
            
            frame.setLayout(new BorderLayout());
            frame.add(queryPanel, BorderLayout.NORTH);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setTitle("Querier");
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);			
		}
		
		frame.repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String text = query.getText();
		runQuery(text);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if ( e.getKeyChar() == '\n' ) {
			runQuery(query.getText());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// do nothing		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// do nothing
	}
	
	private void runQuery(String query) {
		Object[][] records = null;
		String message = null;
		try {
			records = dataSource.executeQuery(query);
			if ( records.length == 1 ) {
				message = "No results found.";
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			message = e.getMessage();
		}
		
		final JDialog results = new JDialog(frame);
		results.setTitle("Results of " + query);
		results.setLayout(new BorderLayout());
		
		if ( message != null ) {
			results.add(new JLabel(message));
		} else {
			Object[][] rowData = new Object[records.length - 1][records[0].length];
			String[] columnNames = new String[records[0].length];
			for ( int i = 0; i < columnNames.length; i++ ) {
				columnNames[i] = (String)records[0][i];
			}
			for ( int j = 1; j < records.length; j++ ) {
				for ( int i = 0; i < columnNames.length; i++ ) {
					rowData[j-1][i] = records[j][i];
				}
			}
			
			JTable table = new JTable(rowData, columnNames);
			JScrollPane scroll = new JScrollPane(table);
			results.add(scroll, BorderLayout.CENTER);
			
			JButton done = new JButton("Done");
			results.add(done, BorderLayout.SOUTH);
			done.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					results.dispose();
				}
				
			});
		}

		results.pack();
		results.setVisible(true);
	}
	
	public static void main(String[] args) {
		Querier q = new Querier(new DataSource() {

			@Override
			public Object[][] executeQuery(String query) {
				return new Object[][] {
					new String[] { "ColumnOne", "ColumnTwo", "ColumnThree" },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 1234, "ValueTwo", new Date() },
					new Object[] { 2345, "ValueThree", new Date(System.currentTimeMillis() + 1243123432) }
				};
			}
			
		});
		q.show();
	}
}