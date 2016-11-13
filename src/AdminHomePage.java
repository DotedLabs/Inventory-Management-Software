

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class AdminHomePage {

	JFrame managerHomePageFrame;
	JButton employeeButton, passwordButton, logoutButton, createEmployeeButton, deleteEmployeeButton, viewEmployeeButton,  changePasswordButton;
	JLabel previousPasswordLabel, newPasswordLabel, confirmNewPasswordLabel;
        JLabel l1, l2, l3,waterMark; //for icons 
	JPasswordField previousPasswordField, newPasswordField, confirmNewPasswordField;
	JPanel employeePanel,changePasswordPanel;

	String adminName;
	String Password;
	
	AdminHomePage(String adminName){
		this.adminName = adminName;
		managerHomePageFrame = new JFrame("Manager Homepage");
                File currentFolder = new File(System.getProperty("user.dir"));  
                ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
                managerHomePageFrame.setIconImage(icon.getImage());
                
		managerHomePageFrame.setLayout(null);
		managerHomePageFrame.setSize(700, 495);
		managerHomePageFrame.setLocation((1366-700)/2, (768-495)/2);
                managerHomePageFrame.setBackground(Color.RED);
		
		employeeButton = new JButton("Employee");
                
		passwordButton = new JButton("Change Password");
                
		logoutButton = new JButton("Logout");
                
		createEmployeeButton = new JButton("Create Employee");
                
		deleteEmployeeButton = new JButton("Delete Employee");
                
		viewEmployeeButton = new JButton("View Employees");
	
		changePasswordButton = new JButton("Change Password");
		
		
		previousPasswordLabel = new JLabel("Enter Previous Password : ");
		newPasswordLabel = new JLabel("Enter New Password : ");
		confirmNewPasswordLabel = new JLabel("Confirm New Password : ");
		l1 = new JLabel();
		l2 = new JLabel();
		l3 = new JLabel();
		
                waterMark = new JLabel();
		
		previousPasswordField = new JPasswordField();
		newPasswordField = new JPasswordField();
		confirmNewPasswordField = new JPasswordField();
                
                
		employeePanel = new JPanel();
		
		changePasswordPanel = new JPanel();
		

		l1.setBounds(40, 150, 150, 150);
		l2.setBounds(250, 150, 150, 150);
		l3.setBounds(460, 150, 150, 150);
		
                waterMark.setBounds(550,-50,200,200);
                
                
		String createEmp = currentFolder.getPath().concat("/images/createEmployee.jpg");
		String deleteEmp = currentFolder.getPath().concat("/images/deleteEmployee.jpg");
		String viewEmp = currentFolder.getPath().concat("/images/viewEmployees.jpg");
		String waterMarkPath = currentFolder.getPath().concat("/images/watermark_white.jpg");
		
                
		try{
                    
		l1.setIcon(new ImageIcon(ImageIO.read(new File(createEmp))));
		l2.setIcon(new ImageIcon(ImageIO.read(new File(deleteEmp))));
		l3.setIcon(new ImageIcon(ImageIO.read(new File(viewEmp))));
		waterMark.setIcon(new ImageIcon(ImageIO.read(new File(waterMarkPath))));
                
                }
                catch(IOException ex) {
			ex.printStackTrace();
		}
		employeePanel.setLayout(null);
		
		changePasswordPanel.setLayout(null);
		employeeButton.setBounds(10, 405, 175, 50);
		
		passwordButton.setBounds(260, 405, 175, 50);
		logoutButton.setBounds(505, 405, 175, 50);
		createEmployeeButton.setBounds(40, 300, 150, 30);
		deleteEmployeeButton.setBounds(250, 300, 150, 30);
		viewEmployeeButton.setBounds(460, 300, 150, 30);
		
		employeePanel.setBounds(0, 0, 700, 400);
		
		changePasswordPanel.setBounds(0, 120, 700, 300);
		
		previousPasswordField.setBounds(260, 50, 250, 30);
		newPasswordField.setBounds(260, 110, 250, 30);
		confirmNewPasswordField.setBounds(260, 170, 250, 30);
		previousPasswordLabel.setBounds(60, 50, 200, 30);
		newPasswordLabel.setBounds(60, 110, 200, 30);
		confirmNewPasswordLabel.setBounds(60, 170, 200, 30);
		changePasswordButton.setBounds(120, 230, 200, 30);
		
		
		changePasswordPanel.setVisible(false);

		employeePanel.setOpaque(false);
		
		
		employeePanel.add(createEmployeeButton); 
		employeePanel.add(deleteEmployeeButton); 
		employeePanel.add(viewEmployeeButton); 
                
		employeePanel.add(l1);
		employeePanel.add(l2);
		employeePanel.add(l3);
                
		
		changePasswordPanel.add(previousPasswordLabel);
		changePasswordPanel.add(newPasswordLabel);
		changePasswordPanel.add(confirmNewPasswordLabel);
		changePasswordPanel.add(previousPasswordField);
		changePasswordPanel.add(newPasswordField);
		changePasswordPanel.add(confirmNewPasswordField);
		changePasswordPanel.add(changePasswordButton);
		
		managerHomePageFrame.add(employeeButton);
	
		managerHomePageFrame.add(passwordButton);
		managerHomePageFrame.add(logoutButton);
		managerHomePageFrame.add(employeePanel);
		
		managerHomePageFrame.add(changePasswordPanel);
                managerHomePageFrame.add(waterMark);
		managerHomePageFrame.setResizable(false);
		managerHomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		managerHomePageFrame.setVisible(true);
                managerHomePageFrame.getContentPane().setBackground(Color.WHITE);
		
                
		employeeButton.addActionListener(new ActionListener(){
                        
			public void actionPerformed(ActionEvent ae) {

				changePasswordPanel.setVisible(false);
				employeePanel.setVisible(true);
                               
			}
		});

                
		
		passwordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
                            
				employeePanel.setVisible(false);
				
                                changePasswordPanel.setBackground(Color.WHITE);
				changePasswordPanel.setVisible(true);
			}});
		
               
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				managerHomePageFrame.setVisible(false);
				new WelcomeScreen();
			}
		});
                
		
		createEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				new CreateEmployee();
			}
		});
                
	
		deleteEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new DeleteEmployee();
			}
		});
                
		
		viewEmployeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			
				new ViewEmployees();
			}
		});


		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					if(newPasswordField.getText().equals(confirmNewPasswordField.getText())) {
						if(changeAdminPassword(previousPasswordField.getText(), newPasswordField.getText())) {
						JOptionPane.showMessageDialog(changePasswordPanel, 
						"Password Updated Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
						changePasswordPanel.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(changePasswordPanel,
							"Old Password in incorrect", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
						JOptionPane.showMessageDialog(managerHomePageFrame, "New Passwords do not match",
								"Password Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		
	}// End of Constructor

	protected boolean changeAdminPassword(String oldPassword, String newPassword) {

		if(validatePassword(oldPassword)) {
			try {
				PreparedStatement psmt = Connect.con.prepareStatement("update inventory_manager " +
				"set password ='"+newPassword + "' where managerid="+adminName);
				psmt.executeUpdate();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validatePassword(String oldPassword) {

		String pass=null;
		try {
			Statement smt = Connect.con.createStatement();
                        
			ResultSet rs = smt.executeQuery("select password from inventory_manager where managerid ="+adminName);
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(oldPassword)){
                            return true;
                        }
                        
			else {
                            return false;
                        }
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

        public static void main(String[] args){
            
           new AdminHomePage("Meesam");
        }
        
}

