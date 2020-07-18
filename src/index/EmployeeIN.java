/**
 * 
 */
package index;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sql.*;
/**
 * @author Che-Steve
 *
 */
public class EmployeeIN extends JFrame  {

	
	public EmployeeIN() {
		Container cp = getContentPane();
		cp.setLayout(null);
		
		//home panel will messages and request
		JPanel homePanel = new JPanel();
		JButton messages = new JButton("Messages");
		messages.setBackground(Color.CYAN);
		homePanel.add(messages);
		
		
		ImageIcon logingif = new ImageIcon("contact.gif");  // relative to project root (or bin)
        
		JLabel lbl = new JLabel("", logingif, JLabel.CENTER);
		lbl.setBackground(Color.DARK_GRAY);
		lbl.setOpaque(true);
		
		JPanel gift = new JPanel();
		gift.add(lbl);
		gift.setBounds(50, 60, 310,266);
		cp.add(gift);
		
		ImageIcon images = new ImageIcon("chesteve.jpg");  // relative to project root (or bin)
        JLabel lbl1 = new JLabel("", images, JLabel.CENTER);
		lbl1.setBackground(Color.DARK_GRAY);
		lbl1.setOpaque(true);
		
		JPanel gift1 = new JPanel();
		gift1.add(lbl1);
		gift1.setBounds(370, 60, 310,266);
		cp.add(gift1);
		
		ImageIcon images2 = new ImageIcon("contact.gif");  // relative to project root (or bin)
        JLabel lbl2 = new JLabel("", images2, JLabel.CENTER);
		lbl2.setBackground(Color.DARK_GRAY);
		lbl2.setOpaque(true);
		
		JPanel gift2 = new JPanel();
		gift2.add(lbl2);
		gift2.setBounds(50, 336, 300,266);
		cp.add(gift2);
		messages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// when clic messages button
				JOptionPane.showMessageDialog(cp,"Messages");
				//dismis all panels
				homePanel.setVisible(false);
				gift.setVisible(false);
				gift1.setVisible(false);
				gift2.setVisible(false);
				
				JPanel displayMessages = new JPanel();
				JLabel messagefrom = new JLabel("Admin"+"1");
				displayMessages.add(messagefrom);
				JLabel lines = new JLabel("  \n \n");
				displayMessages.add(lines);
				JTextArea sms = new JTextArea(5, 40);
				sms.setText("hello, Mr Kamwaza, today you must develop airwing \n aeroplane tracker and don'nt forget to report to me!!");
				sms.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				sms.setAlignmentX(CENTER_ALIGNMENT);
				sms.setAlignmentY(CENTER_ALIGNMENT);
				sms.setWrapStyleWord(true);
				sms.setAutoscrolls(true);
				sms.setEditable(false);
				displayMessages.add(sms);
				
				JButton copysms = new JButton("Report");
				displayMessages.add(copysms);
				
				
				displayMessages.setBounds(200, 10, 300, 1800);
				cp.add(displayMessages);
			}
		
		});
		
		JButton request = new JButton("Request Assignment");
		request.setBackground(Color.yellow);
		homePanel.add(request);
		
		JButton report = new JButton("Report");
		report.setBackground(Color.white);
		homePanel.add(report);
		
		JButton logout = new JButton("Logout");
		logout.setBackground(Color.red);
		homePanel.add(logout);
	

//		ImageIcon images = new ImageIcon("contact.gif");  // relative to project root (or bin)
//        JLabel lbl1 = new JLabel("", images, JLabel.CENTER);
//		lbl1.setBackground(Color.DARK_GRAY);
//		lbl1.setOpaque(true);
//		
//		JPanel gift1 = new JPanel();
//		gift1.add(lbl1);
//		gift1.setBounds(370, 60, 310,266);
//		cp.add(gift1);

		
		
		homePanel.setBackground(Color.DARK_GRAY);
		homePanel.setBounds(100,10, 600, 40);
		cp.add(homePanel);
		cp.setBackground(Color.lightGray);
		cp.setVisible(true);
		setVisible(true);
		setSize(800,800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String []argd) {
		new EmployeeIN();
	}

}