package pfgmoney.letter.app;



import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class UiApp {
	
	public PrintStream originalOut = System.out;
	public PrintStream logFile = System.out;
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiApp window = new UiApp();
					window.frame.setVisible(true);
					
					
					//FOR WRITING LOG FILES
//					PrintWriter writer = new PrintWriter("C:\\Users\\Azima\\Desktop\\log.txt", "UTF-8");
//					writer.println("The first line");
//					writer.println("The second line");
//					writer.close();
				
					
					
				
				
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UiApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton searchButton = new JButton("Search for letters");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CLICK ACTION
				SearchLetters s = null;
				s.main(null); 
				frame.dispose();
			
			}

		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, searchButton, 25, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, searchButton, 26, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, searchButton, -26, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().setLayout(springLayout);
		frame.getContentPane().add(searchButton);
		
		JButton Upload = new JButton("Upload into \ncompany's folder");
		springLayout.putConstraint(SpringLayout.SOUTH, searchButton, -30, SpringLayout.NORTH, Upload);
		springLayout.putConstraint(SpringLayout.EAST, Upload, 0, SpringLayout.EAST, searchButton);
		springLayout.putConstraint(SpringLayout.WEST, Upload, 0, SpringLayout.WEST, searchButton);
		springLayout.putConstraint(SpringLayout.NORTH, Upload, 168, SpringLayout.NORTH, frame.getContentPane());
		Upload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CLICK ACTION
				System.out.println("logged in as : THOMAS 10/10/2019 1.53 pm");
				UploadLetters s = null;
				s.main(null); 
				frame.dispose();
			}
		});
		frame.getContentPane().add(Upload);
		
		JButton btnNewButton_2 = new JButton("EXIT");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_2, 322, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_2, 28, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, searchButton);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CLICK ACTION
				System.exit(0);
			}
		});
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_2, -40, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, Upload, -194, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.NORTH, panel, 475, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 572, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(panel);
		
	}

}
