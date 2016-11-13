

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateEmployee {

	JFrame empolyeeFrame ;
	JLabel empolyeeIDLabel, empolyeeNameLabel, passwordLabel, dateOfJoinLabel, addressLabel, emailIDLabel, contactNoLabel;
	JTextField employeeIDTextField, employeeNameTextField, dateOfJoinTextField, emailIDTextField, contactNoTextFeild;
	JPasswordField passwordPasswordField;
	JTextArea addressTextArea;
	JButton createButton, backButton;
	
	
	private int employeeid;
	private String employeename;
	private String password;
	private String dateofjoin;
	private String address;
	private String emailid;
	private String contactno;
	
        
	public CreateEmployee() {
	
		empolyeeFrame = new JFrame("New Employee Form");
                File currentFolder = new File(System.getProperty("user.dir"));  
                ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
                empolyeeFrame.setIconImage(icon.getImage());
                
		empolyeeIDLabel = new JLabel("Empolyee ID : ");
		empolyeeNameLabel = new JLabel("Employee Name  : ");
		passwordLabel = new JLabel("Password  : ");
		dateOfJoinLabel = new JLabel("Date Of Joining  : ");
		addressLabel = new JLabel("Address: ");
		emailIDLabel = new JLabel("Email ID : ");
		contactNoLabel = new JLabel("Contact No. : ");
		employeeIDTextField = new JTextField();
		employeeNameTextField = new JTextField();
		dateOfJoinTextField = new JTextField();
		emailIDTextField = new JTextField();
		contactNoTextFeild = new JTextField();
		
		passwordPasswordField = new JPasswordField();
		addressTextArea = new JTextArea();
		createButton = new JButton("Create");
		backButton = new JButton("Back");
		
		
		empolyeeIDLabel.setBounds(40, 50, 140, 30);
		empolyeeNameLabel.setBounds(40, 100, 140, 30);
		passwordLabel.setBounds(40, 150, 140, 30);
		dateOfJoinLabel.setBounds(40, 200, 140, 30);
		addressLabel.setBounds(40, 250, 140, 30);
		emailIDLabel.setBounds(40, 370, 140, 30);
		contactNoLabel.setBounds(40, 420, 140, 30);
		
		employeeIDTextField.setBounds(200, 50, 300, 30);
		employeeNameTextField.setBounds(200, 100, 300, 30);
		passwordPasswordField.setBounds(200, 150, 300, 30);
		dateOfJoinTextField.setBounds(200, 200, 300, 30);
		addressTextArea.setBounds(200, 250, 300, 100);
		emailIDTextField.setBounds(200, 370, 300, 30);
		contactNoTextFeild.setBounds(200, 420, 300, 30);
		
		createButton.setBounds(80, 480, 150, 30);
		backButton.setBounds(270, 480, 120, 30);
		
	
		empolyeeFrame.setLayout(null);
		empolyeeFrame.setSize(540, 550);
		empolyeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		empolyeeFrame.setLocation((1366-540)/2, (768-550)/2);
		
		empolyeeFrame.setResizable(false);
		empolyeeFrame.add(empolyeeIDLabel);
		empolyeeFrame.add(empolyeeNameLabel);
		empolyeeFrame.add(passwordLabel);
		empolyeeFrame.add(dateOfJoinLabel);
		empolyeeFrame.add(addressLabel);
		empolyeeFrame.add(emailIDLabel);
		empolyeeFrame.add(contactNoLabel);
		empolyeeFrame.add(employeeIDTextField);
		empolyeeFrame.add(employeeNameTextField);
		empolyeeFrame.add(dateOfJoinTextField);
		empolyeeFrame.add(emailIDTextField);
		empolyeeFrame.add(addressTextArea);
		empolyeeFrame.add(passwordPasswordField);
		empolyeeFrame.add(contactNoTextFeild);
		empolyeeFrame.add(createButton);
		empolyeeFrame.add(backButton);
		
		empolyeeFrame.setVisible(true);
		
		createButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				if(validateForm()) {
					initializeDetails();
					if(createRecord()) {
					JOptionPane.showMessageDialog(empolyeeFrame, "Employee Created");
					empolyeeFrame.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(empolyeeFrame, "Record Not Created");
					}
				}
				else{
						JOptionPane.showMessageDialog(empolyeeFrame,  "Fill all the details", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				empolyeeFrame.setVisible(false);
				
			}
		});
	}

	private boolean validateForm() {
		if(((employeeIDTextField.getText().length())==0)||((employeeNameTextField.getText().length())==0)||((dateOfJoinTextField.getText().length())==0)
				||((passwordPasswordField.getText().length())==0)||contactNoTextFeild.getText().length()==0)
			return false;
		else 
			return true;
	}
	
	
	public void initializeDetails() {
		try {
		employeeid = Integer.parseInt(employeeIDTextField.getText());
		employeename = employeeNameTextField.getText();
		password = passwordPasswordField.getText();
		dateofjoin = dateOfJoinTextField.getText();
		address = addressTextArea.getText();
		emailid = emailIDTextField.getText();
		contactno = contactNoTextFeild.getText();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(empolyeeFrame, "Enter Valid Details", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean createRecord() {
		
		try{
			if(address.length()!=0) {
			PreparedStatement psmt = Connect.con.prepareStatement("insert into inventory_emp values(?, ?, ?, ?, ?, ?, ?,0)");
			psmt.setInt(1, employeeid);
			psmt.setString(2, employeename);
			psmt.setString(3, password);
			psmt.setString(4, dateofjoin);
			psmt.setString(5, address);
			psmt.setString(6, emailid);
			psmt.setString(7, contactno);
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(empolyeeFrame, "New Employee Created Successfully!");
			return true;
			}
			else{
			
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(empolyeeFrame, "Invalid Details", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
        
        public static void main(String[] args){
            new CreateEmployee();
        }
}

