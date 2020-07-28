package employee;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.sql.*;
import javax.swing.*;

public class Login extends JFrame {
	
	private JLabel emaillabel;
	private JTextField email;
	private JLabel passwordlabel;
	private JPasswordField password;
	private JButton login;
	private JButton reset;
	private String emailvar;
	private String passwordvar;
	
	
	public Login() {
		
		Container cp = getContentPane();
		JPanel texure = new JPanel(new GridLayout(3, 2, 0, 0));
		JLabel h1 = new JLabel("LOGIN");
		JTextField text = new JTextField(20);
		text.setEditable(false);
		texure.add(h1);
		texure.add(text);
		texure.setBackground(Color.CYAN);
		texure.setSize(300, 200);
		
		
		JPanel mainPanel = new JPanel(new GridLayout(3, 2, 0, 0));
		
		
	   
	      
		emaillabel = new JLabel("Email :");
		mainPanel.add(emaillabel);
		
		
		email = new JTextField("",15);
		email.setEditable(true);
		email.setBackground(Color.WHITE);
		email.setBorder(BorderFactory.createLineBorder(Color.CYAN, 5));
		
		mainPanel.add(email);
		
		passwordlabel = new JLabel("Password :");
		mainPanel.add(passwordlabel);
		
		password = new JPasswordField(15);
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
	  			  	String strSelect = "select username, password from captains";
	  		      
	  		         ResultSet rset = stm.executeQuery(strSelect);
	  		         
	  		         
	  		         boolean loginValue = true;
	  		         for(int i=0;rset.next(); i++) {   // Move the cursor to the next row, return false if no more row
	  		            String emai = rset.getString("username");
	  		            String pass = rset.getString("password");
	  		            
	  		           if(useremail.contains(emai) && userPassword.contains(pass)) {
	  		            	JOptionPane.showMessageDialog(cp, "Successfully login");
	  		            	
	  		            	String accountusername =emai;
	  		            	String accountpass=pass;
	  		            	
	  		            	//reseting the login feilds
	  		            	loginValue=false;
	  		            	email.setText("");
	  		            	password.setText("");
	  		            	
	  		            	 new Employee();
	  		            	
	  		            	break;
	  		            }
	  		           
	  		        }
	  		        if(loginValue) {
	  		        	JOptionPane.showMessageDialog(cp, "Incorrect Login Details");
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
		mainPanel.setSize(300, 200);
		reset.setBackground(Color.RED);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				email.setText("");
				password.setText("");
			}
			
		});
		
		
		
		
		JPanel display = new JPanel(new FlowLayout());
		display.add(texure);
		mainPanel.setBackground(Color.WHITE);

		display.add(mainPanel);
		display.setSize(300, 200);
		display.setBackground(Color.BLUE);
		cp.add(display);
		cp.setBackground(Color.DARK_GRAY);
		cp.setLayout(new FlowLayout());
		cp.setSize(600,400);
		cp.setVisible(true);
		setTitle("Employee MS");
		setVisible(true);
		
		setSize(800,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
	}



	public static void main(String[] args) {
		new Login();

	}


}
