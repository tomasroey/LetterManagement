package pfgmoney.letter.app;

import java.awt.EventQueue;





import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;import java.util.TreeMap;

import javax.swing.JMenuItem;
import javax.swing.JComboBox;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class UploadLetters {

	private JFrame frame;
	public int flag=0;
	public int flag2=0;
	public int flag3=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UploadLetters window = new UploadLetters();
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
	public UploadLetters() {
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
		companyNames.add("Company");
		companyNames.add("PFG Money");
		companyNames.add("PFG Forex");
		companyNames.add("Lacsa");
		companyNames.add("Lacs");
		companyNames.add("Other");
		
		JComboBox companyList = new JComboBox();
		companyList.setBounds(149, 72, 146, 22);
		frame.getContentPane().add(companyList);
		companyList.setModel(new DefaultComboBoxModel(companyNames.toArray()));
		
		companyList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag=1;
				}});
		
		
		List<String> senderList = new ArrayList<String>();//TO BE READ FROM TEXT FILE_TO BE CHANGED
		senderList.add("Sender");
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
		letterTypes.setBounds(149, 130, 146, 22);
		frame.getContentPane().add(letterTypes);
		
		List<String> letter_type = new ArrayList<String>();
		letter_type.add("Letter type");
		letter_type.add("Correspondence");
		letter_type.add("Statement");
		letter_type.add("Invoice");
		letter_type.add("Other");	
		
		letterTypes.setModel(new DefaultComboBoxModel(letter_type.toArray()));
		
		
		letterTypes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag2=1;
				}});
		
		JComboBox sender = new JComboBox();
		sender.setBounds(149, 101, 146, 22);
		frame.getContentPane().add(sender);
		sender.setModel(new DefaultComboBoxModel(senderList.toArray()));
		sender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag3=1;
				}});
		
		JButton btnSearch = new JButton("Load Recent Scans");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//write code: only proceed below if all the flags are equal to one
				
				
				File dir=new File("C:\\");//ORIGINAL UPLOADS PATH_ Load Recent scans
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
		btnSearch.setBounds(328, 186, 147, 25);
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
		btnExit.setBounds(328, 224, 147, 25);
		frame.getContentPane().add(btnExit);
		
		
		//RENAMING AND MOVING FILES
		JButton btnNewButton = new JButton("Rename and move \n to the respective folder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File sourceFile = new File("C:\\Users\\Azima\\Desktop\\yo.txt");
				File destFile = new File("C:\\Users\\Azima\\Desktop\\lalala\\yoyoyoyo.txt");
				
				if (sourceFile.renameTo(destFile)) {
				    System.out.println("File moved successfully");
				} else {
				    System.out.println("Failed to move file");
				}
				
				//UPDATING TULLAMARINE Letter REGISTER
				try {
					FileInputStream file = new FileInputStream(new File("C:\\Users\\Azima\\Desktop\\we.xlsx"));
					 // Create Workbook instance holding reference to .xlsx file 
		            XSSFWorkbook workbook = new XSSFWorkbook(file); 
		  
		            // Get first/desired sheet from the workbook 
		            XSSFSheet sheet = workbook.getSheetAt(1); 
		            System.out.println(sheet.getSheetName());
//		            String prevSlNo="asdad";
		            // Iterate through each rows one by one 
		            Iterator<Row> rowIterator = sheet.iterator(); 
		           int rowCount =0;
		           
		            while (rowIterator.hasNext()) { 
		            	rowCount++;
		                Row row = rowIterator.next(); 
//		                prevSlNo=row.getCell(0).toString();
		                // For each row, iterate through all the columns 
//		                Iterator<Cell> cellIterator = row.cellIterator(); 
		  
		            }
		                //reading each cells
//		                while (cellIterator.hasNext()) { 
//		                    Cell cell = cellIterator.next(); 
//		                    System.out.println(cell.getCellType().toString());
//		                    // Check the cell type and format accordingly 
//		                    switch (cell.getCellType()) { 
//		                    case NUMERIC : 
//		                        System.out.print(cell.getNumericCellValue() + "\t"); 
//		                        break; 
//		                    case STRING: 
//		                        System.out.print(cell.getStringCellValue() + "\t"); 
//		                        break; 
//		                    } 
//		                	}
//		                	Map<String, Object[]> data = new TreeMap<String, Object[]>(); 
				           
//				            data.put("Inward"+"companyNAme"+)
//				            data.put("1", new Object[]{ "ID", "lalala", "LASTNAME" }); 
//				            data.put("2", new Object[]{ 1, "Pankaj", "Kumar" }); 
//				            data.put("3", new Object[]{ 2, "Prakashni", "Yadav" }); 
//				            data.put("4", new Object[]{ 3, "Ayan", "Mondal" }); 
//				            data.put("5", new Object[]{ 4, "Virat", "kohli" }); 
				      
				            // Iterate over data and write to sheet 
//				            Set<String> keyset = data.keySet(); 
//				            int rownum = rowCount; 
//				            for (String key : keyset) { 
				                // this creates a new row in the sheet 
//				                Row row = sheet.createRow(rownum++); 
//				                Object[] objArr = data.get(key); 
//				                int cellnum = 0; 
//				                for (Object obj : objArr) { 
				                    // this line creates a cell in the next column of that row 
//				                   Cell cell = row.createCell(cellnum++); 
//				                    if (obj instanceof String) 
//				                        cell.setCellValue((String)obj); 
//				                    else if (obj instanceof Integer)
//				                        cell.setCellValue((Integer)obj); 
//				                } 
//				            } 
				                // this Writes the workbook  
				            	Row row = sheet.createRow(rowCount++); 
				            	float serial=3424342;//Float.parseFloat(prevSlNo);
				            	serial++;
				            	String d =Float.toString(serial);
				            	System.out.println(d);
				            	//getting local date
				            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				            	Calendar cal = Calendar.getInstance();
				            	System.out.println(dateFormat.format(cal.getTime()));
				            	String[] contents={"asd","ssss"};
//				               	String[] contents={d,"Inward",letterTypes.getSelectedItem().toString(),
//				               			companyList.getSelectedItem().toString(),"NA",sender.getSelectedItem().toString(),
//				               			"PFG Money Operations",dateFormat.format(cal.getTime())};
				            	int cellnum = 0;
				            	 for (String obj : contents) { 
//					              this line creates a cell in the next column of that row 
					                   Cell cell = row.createCell(cellnum++); 
					                   cell.setCellValue((String)obj); 
					                } 
				                FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Azima\\Desktop\\we.xlsx")); 
				                workbook.write(out); 
				                out.close(); 
				                System.out.println("we.xlsx written successfully on disk."); 
				            
				            
		                System.out.println(""); 
		            
		            file.close(); 
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
			}
		});
		btnNewButton.setBounds(12, 186, 290, 48);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
	}
}
