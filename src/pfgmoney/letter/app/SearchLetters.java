package pfgmoney.letter.app;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JMenuItem;
import javax.swing.JComboBox;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Panel;

import javax.swing.JList;
import javax.swing.JScrollPane;

public class SearchLetters {

	private JFrame frame;
	private String buffaloName="PFG8TB";
	private String pfg="PFGMoney";
	private String pfgfx="PFGForex";
	private String lacsa="Lacsa";
	private String lacs="Lacs";	
	private String operations="Operations";
	private String companyManagement="Company Management";
	private String employeeManagement="Employee Management";
	private String banksandproviders="Banks and Providers";
	private String correspondence="Correspondence";
	private String invoice="Invoice";
	private String statements="Statements";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchLetters window = new SearchLetters();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchLetters() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 526, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSelectCompany = new JLabel("Select Company");
		lblSelectCompany.setBounds(12, 75, 114, 16);
		frame.getContentPane().add(lblSelectCompany);
		
		JLabel lblSelectSender = new JLabel("Select Sender");
		lblSelectSender.setBounds(12, 104, 114, 16);
		frame.getContentPane().add(lblSelectSender);
		
		JLabel lblSelectLetterType = new JLabel("Select letter type");
		lblSelectLetterType.setBounds(12, 133, 114, 16);
		frame.getContentPane().add(lblSelectLetterType);
		
		List<String> companyNames = new ArrayList<String>();
		companyNames.add("PFG Money");
		companyNames.add("PFG Forex");
		companyNames.add("Lacsa");
		companyNames.add("Lacs");
		companyNames.add("Other");
		
		JComboBox companyList = new JComboBox();
		companyList.setBounds(149, 72, 246, 22);
		frame.getContentPane().add(companyList);
		companyList.setModel(new DefaultComboBoxModel(companyNames.toArray()));
		
		List<String> senderList = new ArrayList<String>();//TO BE READ FROM TEXT FILE_TO BE CHANGED
		senderList.add("ANZ");
		senderList.add("NAB");
		senderList.add("WestPac");
		senderList.add("Merchant Mortgages");
		senderList.add("Telstra");
		senderList.add("Fuji Xerox");
		senderList.add("Citywest Water");
		senderList.add("Simple Energy");
		senderList.add("Hume City Council");
		senderList.add("Australian Taxation Office");
		senderList.add("Bank Of Ceylon");
		senderList.add("BDO Unibank");


		JComboBox letterTypes = new JComboBox();
		letterTypes.setBounds(149, 130, 246, 22);
		frame.getContentPane().add(letterTypes);
		
		List<String> letter_type = new ArrayList<String>();
		letter_type.add("Correspondence");
		letter_type.add("Statement");
		letter_type.add("Invoice");
		letter_type.add("Other");	
		
		letterTypes.setModel(new DefaultComboBoxModel(letter_type.toArray()));
		
		JComboBox sender = new JComboBox();
		sender.setBounds(149, 101, 246, 22);
		frame.getContentPane().add(sender);
		sender.setModel(new DefaultComboBoxModel(senderList.toArray()));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String company = companyList.getSelectedItem().toString();
				String letter = letterTypes.getSelectedItem().toString();
				String senders = sender.getSelectedItem().toString();
				int i=0;
				if(company=="PFG Money"){
					 i=1;
				}
				else if(company=="PFG Forex"){
					 i=2;
				}
				else if(company=="Lacsa"){
					 i=3;
				}
				else if(company=="Lacs"){
					 i=4;
				}
				String filePath=null;
				switch(i){
							case 1: System.out.println("pfg");
							//  \\PFG8TB\\1_PFGMoney_Operations\\3. Banks and Providers\\ANZ,NAB, WBC westpac\\Corre
							if(senders.equalsIgnoreCase("ANZ") || senders.equalsIgnoreCase("NAB") || senders.equalsIgnoreCase("Westpac") || senders.equalsIgnoreCase("CBA")){
								filePath="\\"+buffaloName+"\\"+"1_"+pfg+"_"+operations+"\\3. "+banksandproviders+"\\"+senders+"\\"+letter;
							}else if(senders.equalsIgnoreCase("Employsure") || senders.equalsIgnoreCase("Allianz")){
								filePath="\\"+buffaloName+"\\"+"1_"+pfg+"_"+operations+"\\6. "+employeeManagement+"\\"+senders+"\\"+letter;
							}
									break;
							case 2:System.out.println("forex");
									break;
							case 3:System.out.println("Lacsa");
							// \\PFG8TB\\2_Lacsa_Operations\\2. Banks and Providers\\Merchant Mortgages,Westpac\\Correspondence, Invoice, Statements
							//	\\PFG8TB\\2_Lacsa_Operations\\4. Company Management\\ATO\\Correspondence, Statements
							//   \\PFG8TB\\2_Lacsa_Operations\\5. Employee Management\\Allianz\\Invoice
									break;
							case 4:System.out.println("Lacs");
									break;
				}
				System.out.println(company);
				File dir=new File(filePath);//company name+operation+sender+letter type (to be changed)
				if(!dir.exists()){
				System.out.println("File does not exist.....");
				}
				String [] fileNames=dir.list();
				JList list = new JList(fileNames);
				list.setBounds(12, 251, 484, 308);
				frame.getContentPane().add(list);
				list.setModel(new DefaultComboBoxModel(fileNames));
				
				list.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				        JList list = (JList)evt.getSource();
				        if (evt.getClickCount() == 2) {

				            // Double-click detected
				            int index = list.locationToIndex(evt.getPoint());
				           
				           Desktop d=Desktop.getDesktop();
				           File selected=new File(dir.getPath()+fileNames[index]);//opening selected element on doubleclick
				           try {
							d.open(selected);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				            
				        } else if (evt.getClickCount() == 3) {

				            // Triple-click detected
				            int index = list.locationToIndex(evt.getPoint());
				        }
				    }
				});
			
				
				//added scroll bar for the file list
				JScrollPane scrollPane = new JScrollPane(list);
				scrollPane.setBounds(12, 251, 484, 308);
				frame.getContentPane().add(scrollPane);
				
				//getting selected items from combo box
				String.valueOf(sender.getSelectedItem());
				
			}
		});
		btnSearch.setBounds(27, 198, 97, 25);
		frame.getContentPane().add(btnSearch);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UiApp s = null;
				s.main(null);
				frame.dispose();
			}
		});
		btnHome.setBounds(27, 23, 97, 25);
		frame.getContentPane().add(btnHome);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// CLICK ACTION
				System.exit(0);
			}
		});
		btnExit.setBounds(370, 198, 97, 25);
		frame.getContentPane().add(btnExit);
		
		
		
		
	
		
		
		
	}
}
