package employee;
/**
 * @author Che-Steve
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.sql.*;
import javax.swing.*;

public class Employee extends JFrame {
	//creating commponets 
	private JButton display, add,detele, sendMessage, update;
	private JTextField texxt;
	private JLabel mylabe ,empty;
	private JPanel addmenu;
	private ImageIcon ico;
	public Employee() {
		Container cp = getContentPane();
		cp.setLayout(null);
		           
		JPanel mainPanel = new JPanel(new FlowLayout());
		
		display = new JButton("DisplayEmployee Names");
		display.setBackground(Color.GREEN);
		mainPanel.add(display);
		display.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(false);
							
				JPanel displayPanel = new JPanel();
				
				JMenu mn = new JMenu("Home");
				mn.setForeground(Color.BLUE);
				displayPanel.add(mn);
				
				JMenu mn2 = new JMenu("FirstName");
				mn2.setForeground(Color.BLUE);
				displayPanel.add(mn2);
				
				JMenu mn3 = new JMenu("LastName");
				mn3.setForeground(Color.BLUE);
				displayPanel.add(mn3);
				
				JMenu mn4 = new JMenu("Depart");
				mn4.setForeground(Color.BLUE);
				displayPanel.add(mn4);
				
				JButton cancel = new JButton("cancel");
				displayPanel.add(cancel);
				cancel.setBackground(Color.YELLOW);
				cancel.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						//set the frame invisible and recall the function
						cp.setVisible(false);
						setVisible(false);
						new Employee();					
					}
					
				});

				JButton exs = new JButton("Exist");
				exs.setBackground(Color.RED);
				displayPanel.add(exs);
				exs.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//exit the menu
						Login login = new Login();
						login.main(null);

						System.exit(1);
					}
					
				});
				try(
		  				Connection conn =  DriverManager.getConnection(
		  			               "jdbc:mysql://localhost:3306/employeedb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		  			               "chesteve", "che");
		  				Statement stm = conn.createStatement();
		  			){
						
		  			  	String strSelect = "select id,empID, firstname,lastname,depID from employee";
		  		      
		  		         ResultSet selectResults = stm.executeQuery(strSelect);
		  		         while(selectResults.next()) {
		  		        	 
		  		        	
		  		        	JTextField fname= new JTextField(selectResults.getString("firstname"),10);
		  		        	fname.setEditable(false);
		  		        	displayPanel.add(fname); 
		  		        	
		  		        	JTextField lname= new JTextField(selectResults.getString("lastname"),10);
		  		        	lname.setEditable(false);
		  		        	displayPanel.add(lname);
		  		        	
		  		        	JTextField emp= new JTextField(selectResults.getString("empID"),10);
		  		        	emp.setEditable(false);
		  		        	displayPanel.add(emp);
		  		        	
		  		        	JTextField dept= new JTextField(selectResults.getString("depID"),10);
		  		        	dept.setEditable(false);
		  		        	displayPanel.add(dept);
		  		        	
		  		        	JButton id = new JButton("Assign");
		  		        	id.setBackground(Color.GREEN);
		  		        	int idNumber =Integer.parseInt(selectResults.getString("id"));
		  		        	id.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// assingning message to an employeee
									//
									displayPanel.setVisible(false);
									
									
									//JOptionPane.showMessageDialog(cp, idNumber);
									
									//defining a gift of flag
									ImageIcon logingif = new ImageIcon("mw.gif");
							        
									JLabel lbl = new JLabel("", logingif, JLabel.CENTER);
									lbl.setBackground(Color.DARK_GRAY);
									lbl.setOpaque(true);
									
			
									
									JPanel messagePanelTo = new JPanel();
									messagePanelTo.setLayout(new FlowLayout());
									setTitle("Send Message");
									
									JLabel from = new JLabel("From : ");
									messagePanelTo.add(from);
									
									JLabel fromline = new JLabel("\n");
									messagePanelTo.add(fromline);
									
									JTextField fromName = new JTextField(30);
									messagePanelTo.add(fromName);
									
									//select lname of the employee
									
									String sql = "select firstname,lastname,depID from employee where id="+idNumber;
									
						  		    
					  		        try {
					  		        	Connection connnet =  DriverManager.getConnection(
						  			               "jdbc:mysql://localhost:3306/employeedb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
						  			               "chesteve", "che");
						  				Statement statm = connnet.createStatement();
										ResultSet employyee = statm.executeQuery(sql);
										while(employyee.next()) {
											String fromNamefirst= employyee.getString("firstname");
											String fromNamelast= employyee.getString("lastname");
											JLabel to = new JLabel("To :       ");
											messagePanelTo.add(to);
											
											JTextField toName = new JTextField(30);
											String nameTo = "Prof/Dr/Mr/Miss "+fromNamefirst+" "+fromNamelast+ " ";
											toName.setText(nameTo);
											toName.setEditable(false);
											messagePanelTo.add(toName);
											
											JTextArea messagText = new JTextArea("Message",5, 35);
											messagText.setAutoscrolls(true);
											//messagText.getScrollableTracksViewportWidth();
											messagText.setEditable(true);
											messagePanelTo.add(messagText);
											
											JButton sendMessage = new JButton("Send");
											sendMessage.setBackground(Color.green);
											//send message to employee id = idanumber
											sendMessage.addActionListener(new ActionListener() {

												@Override
												public void actionPerformed(ActionEvent e) {
													String sqlin = "INSERT INTO message(mtext,fromNa,id) VALUES (?,?,?)";
													PreparedStatement statement;
													try {
														statement = connnet.prepareStatement(sqlin);
														statement.setString(1,messagText.getText());
														statement.setString(2,fromName.getText() );
														statement.setInt(3,idNumber);
														
														int countN = statement.executeUpdate();
												         
												         if(countN>0) {
												        	JOptionPane.showMessageDialog(cp, "Message Sent!!");
												        	messagePanelTo.setVisible(false);
												        	
												        	displayPanel.setVisible(true);
												        	
												         }
												         connnet.close();
													} catch (SQLException e1) {
														// TODO Auto-generated catch block
														e1.printStackTrace();
													}
													

													
												}
												
											});
											messagePanelTo.add(sendMessage);
											
										}
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					  		        
									//messagePanelTo.setVisible(true);
					  		        JPanel gift = new JPanel();
									gift.add(lbl);
									gift.setBounds(10, 10, 110,90);
									gift.setBackground(Color.WHITE);
									cp.add(gift);
									
									
									messagePanelTo.setBounds(200, 5, 380, 700);
									messagePanelTo.setBackground(Color.gray);
									messagePanelTo.setAutoscrolls(true);
									cp.setBackground(Color.white);
									displayPanel.add(messagePanelTo);
									cp.add(messagePanelTo);
								}
		  		        		
		  		        	});
		  		        	displayPanel.add(id);
		  		        	displayPanel.add(new JLabel("     \n   "));
		  		        	
		  		         }
		  		    
		  		   conn.close();     
				}catch(SQLException e1) {
		  			e1.printStackTrace();					
				}
				displayPanel.setBounds(125, 40, 550, 500);
				
				cp.add(displayPanel);
				
				
				
				
			}
			
			
		});
		
		empty = new JLabel("EMPLOYEE");
		mainPanel.add(empty);
		
		add = new JButton("Add new Employee");
		add.setBackground(Color.ORANGE);
		mainPanel.add(add);
		mainPanel.setBounds(90, 100, 600,600);
		cp.add(mainPanel);
		
		JButton back = new JButton("Back");
		back.setBackground(Color.blue);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cp, "You have Successfully Logout!!");
				Login lg = new Login();
				cp.setVisible(false);
				setVisible(false);
				lg.main(null);
			}
			
		});
		mainPanel.add(back);
		
		JButton logout = new JButton("Logout");
		logout.setBackground(Color.red);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(cp, "You have Successfully Logout!!");
				System.exit(1);
			}
			
		});
		mainPanel.add(logout);
		
		addmenu = new JPanel(new FlowLayout());
		add.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// when click add button 
				
				mainPanel.setVisible(false);
				
				JMenu btn = new JMenu("Home");
				btn.setBackground(Color.DARK_GRAY);
				btn.setForeground(Color.BLUE);
				addmenu.add(btn);
				
				JMenu btn1 = new JMenu("About");
				btn1.setBackground(Color.DARK_GRAY);
				btn1.setForeground(Color.BLUE);
				addmenu.add(btn1);
				
							
				JButton cancel = new JButton("cancel");
				addmenu.add(cancel);
				cancel.setBackground(Color.YELLOW);
				cancel.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						//set the frame invisible and recall the function
						setVisible(false);
						new Employee();					
					}
					
				});

				JButton exs = new JButton("Exist");
				addmenu.add(exs);
				exs.setBackground(Color.RED);
				exs.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//exit the menu
						Login login = new Login();
						login.main(null);

						//System.exit(1);
					}
					
				});
				
				
				
				
				JLabel fnamela = new JLabel("FirstName: ");
				addmenu.add(fnamela);
				JTextField firstname = new JTextField(20);
				addmenu.add(firstname);
				
				JLabel lnamela = new JLabel("LastName: ");
				addmenu.add(lnamela);
				JTextField lastname = new JTextField(20);
				addmenu.add(lastname);
				
				JLabel emploIDla = new JLabel("EmployID : ");
				addmenu.add(emploIDla);
				JTextField employID = new JTextField(20);
				addmenu.add(employID);
				
				JLabel birthday = new JLabel("Age      : ");
				addmenu.add(birthday);
				JTextField birthDate = new JTextField(20);
				addmenu.add(birthDate);
				
				JLabel genderla = new JLabel("Gender : ");
				addmenu.add(genderla);
				JTextField gender = new JTextField(20);
				addmenu.add(gender);
				
				JLabel usernla = new JLabel("UserName: ");
				addmenu.add(usernla);
				JTextField usern = new JTextField(20);
				addmenu.add(usern);
				
				JLabel passla = new JLabel("Password : ");
				addmenu.add(passla);
				JTextField pass = new JTextField(20);
				addmenu.add(pass);
				
				JButton addBu = new JButton("Add");
				addBu.setBackground(Color.green);
				addmenu.add(addBu);
				addBu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// adding items to database
						String fname = firstname.getText();
						String lname = lastname.getText();
						String employeeId= employID.getText();
						int age = Integer.parseInt(birthDate.getText());
						String username = usern.getText();
						String password = pass.getText();
						String gend = gender.getText();
						
						//inserting data in the database						
						try (
								//Establishing connection
							Connection	conn = DriverManager.getConnection(
							           "jdbc:mysql://localhost:3306/employeedb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
							           "chesteve", "che");
							
						){
							
							String sql = "INSERT INTO employee (firstname,lastname,age,empID,username,password,gender) VALUES (?,?,?,?,?,?,?)";
							PreparedStatement statement = conn.prepareStatement(sql);
							statement.setString(1,fname);
							statement.setString(2, lname);
							statement.setInt(3, age);
							statement.setString(4,gend);
							statement.setString(5, username);
							statement.setString(6,password);
							statement.setString(7, gend);
							int count = statement.executeUpdate();
					         
					         if(count>0) {
					        	JOptionPane.showMessageDialog(null, "You have successfully add a new Employee!!");
					        	firstname.setText(" ");
								lastname.setText(" ");
								employID.setText(" ");
								birthDate.setText(" ");
								gender.setText(" ");
								usern.setText(" ");
								pass.setText(" ");
					         }

							
							conn.close();
						} catch (SQLException e1) {
							// when failed to connect to database return to main panel 
							JOptionPane.showMessageDialog(cp, "Unable to Connect to Database");
							addmenu.setVisible(false);
							mainPanel.setVisible(true);
							e1.printStackTrace();
						}
		  				
											
					}
					
				});
				
				
				JButton addreset = new JButton("reset");
				addreset.setBackground(Color.YELLOW);
				addmenu.add(addreset);
				addreset.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// resetting data items in the fields
						firstname.setText(" ");
						lastname.setText(" ");
						employID.setText(" ");
						birthDate.setText(" ");
						gender.setText(" ");
						usern.setText(" ");
						pass.setText(" ");
					}
					
				});
				
				
				//add to cp panel
				addmenu.setBounds(250, 200, 300, 600);
				cp.add(addmenu);
			}
			
		});
		
		String imagea ="chesteve.jpg";
		ico = new ImageIcon(imagea);
		JLabel pic = new JLabel("", ico, JLabel.CENTER);
		mainPanel.add(pic);
		
		cp.setBackground(Color.DARK_GRAY);
		setVisible(true);
		setTitle("Employee MS ADMIN SECTION");
		setVisible(true);
		setResizable(false);
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}

	public static void main(String[] args) {
		//JOptionPane.showMessageDialog(null, "Welcome to admin Section");
		new Employee();
	}

}
