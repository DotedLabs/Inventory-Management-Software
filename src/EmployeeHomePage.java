

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;



public class EmployeeHomePage {
        
        int sid; // Session Id
        
	JFrame employeeHomePageFrame;
        
	JButton inventoryButton, passwordButton, viewProfileButton, changePasswordButton, editDetailsButton, updateDetailsButton;
	
        JLabel previousPasswordLabel,newPasswordLabel, confirmNewPassordLabel, currentWorkingEmployeeLabel, logoutLabel, employeeIDLabel, employeeNameLabel, dateOfJoinLabel, addressLabel, emailIDLabel,contactNoLabel;
       
        JLabel l12, l13, l14, l15, l16, l17;    //used in profile panel to show details of employee
        
     
        
        
	JPasswordField previousPasswordFeild, newPasswordFeild, confirmNewPasswordFeild;
	
        JTextField addressTextFeild, emailIDTextFeild, contactNoTextFeild;
	
        JPanel  changePasswordPanel, profilePanel, editProfilePanel;
	
        String employeeID;
	String employeeName;
	String email;
	String address;
	String dateOfBirth;
	String contact;
	
        final static Color myblue = new Color(6, 13, 83);
	
	EmployeeHomePage(String ID) {
		employeeID = ID;
		employeeHomePageFrame = new JFrame("Employee Homepage");
                
                File currentFolder = new File(System.getProperty("user.dir"));  
                ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
                employeeHomePageFrame.setIconImage(icon.getImage());
                
		employeeHomePageFrame.setLayout(null);
		employeeHomePageFrame.setLocation((1366-700)/2, (768-500)/2);
		employeeHomePageFrame.setSize(700, 500);
                employeeHomePageFrame.setBackground(Color.WHITE);
		
		
		inventoryButton = new JButton("Inventory");
		passwordButton = new JButton("Change Password");
		viewProfileButton = new JButton("View Profile");

		changePasswordButton = new JButton("Change password");
		editDetailsButton = new JButton("Edit Details");
		updateDetailsButton = new JButton("Update Details");
		
		previousPasswordLabel = new JLabel("Enter Previous Password : ");
		newPasswordLabel = new JLabel("Enter New Password : ");
		confirmNewPassordLabel = new JLabel("Confirm New Password : ");
		currentWorkingEmployeeLabel = new JLabel();
		logoutLabel = new JLabel("  Logout");
		employeeIDLabel = new JLabel("Employee ID : ");
		employeeNameLabel = new JLabel("Name : ");
		dateOfJoinLabel = new JLabel("Date Of Join : ");
		addressLabel = new JLabel("Address : ");
		emailIDLabel = new JLabel("Email : ");
		contactNoLabel = new JLabel("Contact No :" );
                
               
                
		l12 = new JLabel();
		l13 = new JLabel();
		l14 = new JLabel();
		l15 = new JLabel();
		l16 = new JLabel();
		l17 = new JLabel();
                
             
		addressTextFeild = new JTextField();
		emailIDTextFeild = new JTextField();
		contactNoTextFeild = new JTextField();
		
		previousPasswordFeild = new JPasswordField();
		newPasswordFeild = new JPasswordField();
		confirmNewPasswordFeild = new JPasswordField();
		
		changePasswordPanel = new JPanel();	
		profilePanel = new JPanel();		
		editProfilePanel = new JPanel();	
		
                BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File(currentFolder.getPath().concat("/images/logoMain.jpg")));
		} 
                catch (IOException e) {	
			e.printStackTrace();
		}
                
                
		employeeHomePageFrame.setContentPane(new ImagePanel(myImage));
		
		changePasswordPanel.setLayout(null);
		profilePanel.setLayout(null);
		editProfilePanel.setLayout(null);
		changePasswordPanel.setOpaque(true);
		profilePanel.setOpaque(true);
		editProfilePanel.setOpaque(true);
		
                changePasswordPanel.setBounds(0, 100, 700, 280);
		profilePanel.setBounds(0, 100, 700, 280);
		editProfilePanel.setBounds(0, 100, 700, 280);
		inventoryButton.setBounds(15, 370, 175, 60);
		
		viewProfileButton.setBounds(250, 370, 175, 60);
		passwordButton.setBounds(490, 370, 175, 60);
		
		changePasswordButton.setBounds(120, 230, 200, 30);
		editDetailsButton.setBounds(350, 50, 150, 50);
		updateDetailsButton.setBounds(350, 50, 150, 50);
                
                
		currentWorkingEmployeeLabel.setBounds(450, 0, 250, 39);
		currentWorkingEmployeeLabel.setBackground(Color.WHITE);
                
                
		logoutLabel.setBounds(600, 39, 100, 30);
		logoutLabel.setOpaque(true);
		logoutLabel.setBackground(Color.WHITE);
		logoutLabel.setForeground(Color.BLACK);
		logoutLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		logoutLabel.setVisible(false);
                
		currentWorkingEmployeeLabel.setOpaque(true);
		
		
		
		previousPasswordFeild.setBounds(260, 50, 250, 30);
		newPasswordFeild.setBounds(260, 110, 250, 30);
		confirmNewPasswordFeild.setBounds(260, 170, 250, 30);
		previousPasswordLabel.setBounds(60, 50, 200, 30);
		newPasswordLabel.setBounds(60, 110, 200, 30);
		confirmNewPassordLabel.setBounds(60, 170, 200, 30);
		
		employeeIDLabel.setBounds(30, 20, 150, 25);
		employeeNameLabel.setBounds(30, 60, 150, 25);
		dateOfJoinLabel.setBounds(30, 100, 150, 25);
		addressLabel.setBounds(30, 140, 150, 25);
		emailIDLabel.setBounds(30, 180, 150, 25);
		contactNoLabel.setBounds(30, 220, 150, 25);
		l12.setBounds(150, 20, 150, 25);
		l13.setBounds(150, 60, 150, 25);
		l14.setBounds(150, 100, 150, 25);
		l15.setBounds(150, 140, 150, 25);
		l16.setBounds(150, 180, 150, 25);
		l17.setBounds(150, 220, 150, 25);
		addressTextFeild.setBounds(150, 140, 150, 25);
		emailIDTextFeild.setBounds(150, 180, 150, 25);
		contactNoTextFeild.setBounds(150, 220, 150, 25);
		
		l12.setBackground(myblue);
		l13.setBackground(myblue);
		l14.setBackground(myblue);
		l15.setBackground(myblue);
		l16.setBackground(myblue);
		l17.setBackground(myblue);
                
                
		l12.setForeground(Color.WHITE);
		l13.setForeground(Color.WHITE);
		l14.setForeground(Color.WHITE);
		l15.setForeground(Color.WHITE);
		l16.setForeground(Color.WHITE);
		l17.setForeground(Color.WHITE);
                
                
		l12.setOpaque(true);
		l13.setOpaque(true);
		l14.setOpaque(true);
		l15.setOpaque(true);
		l16.setOpaque(true);
		l17.setOpaque(true);
		
	
		changePasswordPanel.add(previousPasswordLabel);
		changePasswordPanel.add(previousPasswordFeild);
		changePasswordPanel.add(newPasswordLabel);
		changePasswordPanel.add(newPasswordFeild);
		changePasswordPanel.add(newPasswordFeild);
		changePasswordPanel.add(confirmNewPassordLabel);
		changePasswordPanel.add(confirmNewPasswordFeild);
		changePasswordPanel.add(changePasswordButton);
		
		profilePanel.add(employeeIDLabel);
		profilePanel.add(employeeNameLabel);
		profilePanel.add(dateOfJoinLabel);
		profilePanel.add(addressLabel);
		profilePanel.add(emailIDLabel);
		profilePanel.add(contactNoLabel);
		profilePanel.add(l12);
		profilePanel.add(l13);
		profilePanel.add(l14);
		profilePanel.add(l15);
		profilePanel.add(l16);
		profilePanel.add(l17);
		profilePanel.add(editDetailsButton);
		
		editProfilePanel.add(addressTextFeild);
		editProfilePanel.add(emailIDTextFeild);
		editProfilePanel.add(contactNoTextFeild);
		editProfilePanel.add(updateDetailsButton);
		
		
		employeeHomePageFrame.add(currentWorkingEmployeeLabel);
		
		employeeHomePageFrame.add(changePasswordPanel);
		employeeHomePageFrame.add(profilePanel);
		employeeHomePageFrame.add(editProfilePanel);
		employeeHomePageFrame.add(logoutLabel);
		
		employeeHomePageFrame.add(inventoryButton);
		employeeHomePageFrame.add(passwordButton);
		employeeHomePageFrame.add(viewProfileButton);
		getLabelName();
                
                
                //Upper Left Corner shows name of current employee and used to logout
		currentWorkingEmployeeLabel.setFont(WelcomeScreen.allLabel);
		currentWorkingEmployeeLabel.setText("  "+employeeName+"'s Account");
		employeeHomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
		
		changePasswordPanel.setVisible(false);
		profilePanel.setVisible(false);
		editProfilePanel.setVisible(false);
		employeeHomePageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		employeeHomePageFrame.setVisible(true);
		
		
		
		//Inventory button
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
                                try{
                                    Connect.getConnection();
                                    PreparedStatement pst=null;
                                    
                                    
                                    Statement st= Connect.con.createStatement();
                                    ResultSet rs=st.executeQuery("select max(sessionid) from emp_work");
                                    
                                    if(rs.next()){  //Moves the cursor forward one row from its current position.
                                        sid = rs.getInt("max(sessionid)");
                                        sid++;
                                    }else{
                                        sid = 1;
                                    }
                                   
                                    String sql = "insert into emp_work values(?,?,sysdate,null,null)";
                                    pst = Connect.con.prepareStatement(sql);
                                    pst.setInt(1, sid);
                                    pst.setString(2, employeeID);
                                    pst.execute();                                   
                                    }catch (SQLException e){
                                        JOptionPane.showMessageDialog(null,e);
                                    }
                                
                                java.awt.EventQueue.invokeLater(new Runnable() {
                                    public void run() {
                                        InventoryManagement n = new InventoryManagement(sid);
                                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                                        n.setSize(screenSize.width, screenSize.height);
                                        n.setVisible(true);

                                    }
                                });
                                
				
			}
		});
		
		//Password Button
		passwordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(editProfilePanel.isVisible()) {
					JOptionPane.showMessageDialog(editProfilePanel, "Details not updated");
					editProfilePanel.setVisible(false);
				}
			
				profilePanel.setVisible(false);
                                changePasswordPanel.setBackground(Color.WHITE);
				changePasswordPanel.setVisible(true);
				
			}
		});
		
		//View Profile Button
		viewProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//connect to database
				
				if(editProfilePanel.isVisible()) {
					JOptionPane.showMessageDialog(editProfilePanel, "Details not updated");
					editProfilePanel.setVisible(false);
				}
				
				changePasswordPanel.setVisible(false);
				setDetails();
				profilePanel.add(employeeIDLabel);
				profilePanel.add(employeeNameLabel);
				profilePanel.add(dateOfJoinLabel);
				profilePanel.add(addressLabel);
				profilePanel.add(emailIDLabel);
				profilePanel.add(contactNoLabel);
				profilePanel.add(l12);
				profilePanel.add(l13);
				profilePanel.add(l14);
                                profilePanel.setBackground(Color.WHITE);
				profilePanel.setVisible(true);
				
			}
		});
		
		
		// Change Password Button
		changePasswordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
					if(newPasswordFeild.getText().equals(confirmNewPasswordFeild.getText())) {
						if(ChangeEmployeePassword(previousPasswordFeild.getText(), newPasswordFeild.getText())) {
						JOptionPane.showMessageDialog(changePasswordPanel, 
						"Password Updated Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(changePasswordPanel,
							"Old Password in incorrect", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
						JOptionPane.showMessageDialog(employeeHomePageFrame, "New Passwords do not match",
								"Password Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		// Edit Details Button
		editDetailsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				profilePanel.setVisible(false);
				editProfilePanel.add(employeeIDLabel);
				editProfilePanel.add(employeeNameLabel);
				editProfilePanel.add(dateOfJoinLabel);
				editProfilePanel.add(addressLabel);
				editProfilePanel.add(emailIDLabel);
				editProfilePanel.add(contactNoLabel);
				editProfilePanel.add(l12);
				editProfilePanel.add(l13);
				editProfilePanel.add(l14);
                                editProfilePanel.setBackground(Color.WHITE);
				editProfilePanel.setVisible(true);
			}
		});
		
		// Update Details Button
		updateDetailsButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent ae) {
				if(addressTextFeild.getText().length()!=0 && emailIDTextFeild.getText().length()!=0 && contactNoTextFeild.getText().length()!=0) {
					try {
						long i = Long.parseLong(contactNoTextFeild.getText());
						updateDetails(addressTextFeild.getText(), emailIDTextFeild.getText(), contactNoTextFeild.getText());
					}catch(Exception e) {
						JOptionPane.showMessageDialog(editProfilePanel,"Enter Valid Contact number", 
						"Invalid Details", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(emailIDTextFeild.getText().length()!=0 && contactNoTextFeild.getText().length()!=0) {
					updateDetails(emailIDTextFeild.getText(), contactNoTextFeild.getText());
				}
				else {
					JOptionPane.showMessageDialog(editProfilePanel, "Contact or email cannot be null",
					"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// Top Label with employee Name
		currentWorkingEmployeeLabel.addMouseListener(new MouseListener() {

		
			public void mouseClicked(MouseEvent me) {
					
			}

	
			public void mouseEntered(MouseEvent me) {
				
				currentWorkingEmployeeLabel.setBackground(myblue);
				currentWorkingEmployeeLabel.setForeground(Color.WHITE);
				currentWorkingEmployeeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				logoutLabel.setVisible(true);
				logoutLabel.setBackground(myblue);
				logoutLabel.setForeground(Color.WHITE);
				
			}

			
			public void mouseExited(MouseEvent me) {
				
				currentWorkingEmployeeLabel.setBackground(Color.WHITE);
				currentWorkingEmployeeLabel.setForeground(Color.BLACK);
				currentWorkingEmployeeLabel.setCursor(null);
				logoutLabel.setVisible(false);
				logoutLabel.setBackground(Color.WHITE);
				logoutLabel.setForeground(Color.BLACK);
			}

			
			public void mousePressed(MouseEvent me) {
			}

			
			public void mouseReleased(MouseEvent me){
		       }
			
		});
		
		// Logout Label
		logoutLabel.addMouseListener(new MouseListener() {

			
			public void mouseClicked(MouseEvent me) {
			
				employeeHomePageFrame.setVisible(false);
				new WelcomeScreen();
			}

			
			public void mouseEntered(MouseEvent me) {
				
				currentWorkingEmployeeLabel.setBackground(myblue);
				currentWorkingEmployeeLabel.setForeground(Color.WHITE);
				currentWorkingEmployeeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				logoutLabel.setVisible(true);
			}

			
			public void mouseExited(MouseEvent me) {
				
				currentWorkingEmployeeLabel.setBackground(Color.WHITE);
				currentWorkingEmployeeLabel.setForeground(Color.BLACK);
				currentWorkingEmployeeLabel.setCursor(null);
				logoutLabel.setVisible(false);
			}

		
			public void mousePressed(MouseEvent arg0) {
		        }

			
			public void mouseReleased(MouseEvent me) {				
			}	
		});
		
	}          //end of constructor

	protected boolean ChangeEmployeePassword(String oldPassword, String newPassword) {
		
		if(validatePassword(oldPassword)) {
			try {
				PreparedStatement psmt = Connect.con.prepareStatement("update inventory_emp " +
				"set password ='"+newPassword + "' where empid="+employeeID);
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

	protected boolean validatePassword(String oldPassword) {
		
		String pass=null;
		try {
			Statement smt = Connect.con.createStatement();
			ResultSet rs = smt.executeQuery("select password from inventory_emp where empid ="+employeeID);
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(oldPassword)) return true;
			else return false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	protected void updateDetails(String newAddress, String newEmail, String newContact) {
		try {
			PreparedStatement psmt = Connect.con.prepareStatement("update inventory_emp " +
			"set address='"+newAddress+"', emailid='"+newEmail + "', mobileno=" + newContact +
			"where empid="+employeeID);
			psmt.executeUpdate();
			email = newEmail;
			contact = newContact;
			address = newAddress;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(editProfilePanel, "Details Updated",
		"Success!", JOptionPane.INFORMATION_MESSAGE);
		backToProfile();
	}

	protected void updateDetails(String newEmail, String newContact) {
		
		try {
			PreparedStatement psmt = Connect.con.prepareStatement("update inventory_emp " +
			"set emailid='"+newEmail + "', mobileno=" + newContact +
			"where empid="+employeeID);
			psmt.executeUpdate();
			email = newEmail;
			contact = newContact;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		JOptionPane.showMessageDialog(editProfilePanel, "Details Updated",
		"Success!", JOptionPane.INFORMATION_MESSAGE);
		backToProfile();
	}

        protected void backToProfile() {
		
		editProfilePanel.setVisible(false);
		profilePanel.add(employeeIDLabel);
		profilePanel.add(employeeNameLabel);
		profilePanel.add(dateOfJoinLabel);
		profilePanel.add(addressLabel);
		profilePanel.add(emailIDLabel);
		profilePanel.add(contactNoLabel);
		profilePanel.add(l12);
		profilePanel.add(l13);
		profilePanel.add(l14);
		setinPanel();
		profilePanel.setVisible(true);
	}

	protected void setDetails() {
		
		System.out.println("hey");
		if(email==null||contact==null||dateOfBirth==null) {
		try {
			Statement smt = Connect.con.createStatement();
			ResultSet rs = smt.executeQuery("select * from inventory_emp where empid="+employeeID);
			while(rs.next()) {
				employeeName = rs.getString("empname");
				dateOfBirth = rs.getString("date_join");
				address = rs.getString("address");
				email = rs.getString("emailid");
				contact = rs.getString("mobileno");
				System.out.println(employeeName);
				System.out.println(dateOfBirth);
				System.out.println(address);
				
				System.out.println(email);
				System.out.println(contact);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		}

		setinPanel();
	}

	private void setinPanel() {
		
		l12.setText(employeeID);
		l13.setText(employeeName);
		l14.setText(dateOfBirth);
		l15.setText(address);
		l16.setText(email);
		l17.setText(""+contact);
	}

	private void getLabelName() {
		try {
			Statement smt = Connect.con.createStatement();
			ResultSet rs = smt.executeQuery("select empname from inventory_emp where empid="+employeeID);
			while(rs.next()) {
				employeeName = rs.getString("empname");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			employeeName=null;
		}
	}
        
     public static void main(String[] args){
         new EmployeeHomePage("1");
     }   
}
