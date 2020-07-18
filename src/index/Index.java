package index;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;
import javax.swing.*;

import employee.Employee;
import employee.Login;

//admin will be able to add a employee and delete him/her in asystem.
// admin will update alter employee details

public class Index extends JFrame{
	private JLabel emaillabel;
	private JTextField email;
	private JLabel passwordlabel;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private String emailvar;
	private String passwordvar;
	
	public Index() {
		Container cp = getContentPane();
		cp.setLayout(null);
		
		
		
		
		JPanel mainPanel = new JPanel();
		
		emaillabel = new JLabel("Email :");
		mainPanel.add(emaillabel);
				
		email = new JTextField("",20);
		email.setEditable(true);
		email.setBackground(Color.WHITE);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
		
		mainPanel.add(email);
		
		passwordlabel = new JLabel("Password :");
		mainPanel.add(passwordlabel);
		
		password = new JPasswordField(18);
		password.setEditable(true);
		password.setBackground(Color.WHITE);
		password.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
		mainPanel.add(password);
		
		login = new JButton("Login");
		login.setBackground(Color.GREEN);
		mainPanel.add(login);
		//mainPanel.setSize(150,150);
				
	// for access
		JLabel label= new JLabel();
		
	    login.addActionListener(new ActionListener() {
	      @Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
	    	  String useremail =email.getText();
	    	  String userPassword=password.getText();
	    	  
	    	  
	    	  try(
	  				Connection conn =  DriverManager.getConnection(
	  			               "jdbc:mysql://localhost:3306/employeedb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
	  			               "chesteve", "che");
	  				Statement stm = conn.createStatement();
	  			){
	  			  	String strSelect = "select username, password from employee";
	  		      
	  		         ResultSet rset = stm.executeQuery(strSelect);	  		         
	  		         //logvalue used to detect if the credentials are valid if are not valid it will main its true state
	  		        boolean logvalue = true;
					for(int i= 0; rset.next();i++) {	  		        	
	  		        	 String emai = rset.getString("username");
		  		         String pass = rset.getString("password");
		  		            
		  		         if(useremail.contains(emai) && userPassword.contains(pass)) {
		  		        	 JOptionPane.showMessageDialog(cp, "Successfully login");
		  		            	
		  		            String accountusername =emai;
		  		            String accountpass=pass;
		  		            
		  		            logvalue=false;
		  		            	//reseting the login feilds
		  		            email.setText("");
		  		            password.setText("");
		  		            	
		  		            new Employee();
		  		            //Employee.main(null);
		  		            	
		  		            break;
		  		         }
	  		         }
					
					//when the logvalue is false
					if(logvalue) {
						JOptionPane.showMessageDialog(cp, "Invalid Login Details!!");
						
						email.setText("");
	  		            password.setText("");
	  		            
					}
	  		         //System.out.println("Total number of records = " + rowCount);
	  		 conn.close();
	  		}
	  		catch(SQLException e1) {
	  			e1.printStackTrace();
	  		}
	    	  
				
			}
	      });
		
		
		reset= new JButton("Reset");
		mainPanel.add(reset);
		reset.setBackground(Color.RED);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				email.setText("");
				password.setText("");
			}
			
		});
		JButton admin = new JButton("Admin Login Here");
		admin.setBackground(Color.yellow);
		mainPanel.add(admin);
		admin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login admin = new Login();
				cp.setVisible(false);
				setVisible(false);
				admin.main(null);
				
			}
			
		});
			
		mainPanel.setBackground(Color.WHITE);
		JPanel jlabel =  new JPanel();
		JLabel wlecm = new JLabel("Welcome to Login Page",JLabel.CENTER);
		jlabel.add(wlecm);
		jlabel.setBackground(Color.gray);
		
		jlabel.setBounds(200, 80, 300, 20);
		cp.add(jlabel);
		//mainPanel.setSize(200,250);
		mainPanel.setBounds(200, 100, 300,100);
		cp.add(mainPanel);
		
		ImageIcon logingif = new ImageIcon("msg.gif");  // relative to project root (or bin)
				        
		JLabel lbl = new JLabel("", logingif, JLabel.CENTER);
		lbl.setBackground(Color.DARK_GRAY);
		lbl.setOpaque(true);
		
		JPanel gift = new JPanel();
		gift.add(lbl);
		gift.setBounds(150, 200, 386,266);
		cp.add(gift);
		
		cp.setBackground(Color.DARK_GRAY);
		cp.setSize(600,400);
		cp.setVisible(true);
		setTitle("Employee MS");
		setVisible(true);
		
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}
	public static void main(String [] che) {
		new Index();
	}

}

