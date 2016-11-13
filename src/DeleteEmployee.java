
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.*;

public class DeleteEmployee {

	JFrame deleteEmployeeFrame;
        
	JLabel selectEmployeeIdLabel, employeeNameLabel, addressLabel, contactNoLabel, emailIDLabel;
        
        JLabel employeeNameDetails,addressDetails, contactNoDetails, emailIDDetails;
        
	JComboBox<String>  selectEmployeeIDComboBox;
        
	JButton deleteButton, cancelButton;
        
        
	private String employeename;
	private int employeeid;
	private String address;
	private Long contactno;
	private String emailid;
	
	public DeleteEmployee() {
		deleteEmployeeFrame = new JFrame("Delete Employee");
                File currentFolder = new File(System.getProperty("user.dir"));  
                ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
                deleteEmployeeFrame.setIconImage(icon.getImage());
                
		selectEmployeeIdLabel = new JLabel("Select Employee ID : ");
		employeeNameLabel = new JLabel("Employee Name : ");
		addressLabel = new JLabel("Address : ");
		contactNoLabel = new JLabel("Contact No: ");
		emailIDLabel = new JLabel("Email ID: ");
		employeeNameDetails = new JLabel();
		addressDetails = new JLabel();
		contactNoDetails = new JLabel();
		emailIDDetails = new JLabel();
		
		selectEmployeeIDComboBox = new JComboBox<String>();
		deleteButton = new JButton("Delete");
		cancelButton = new JButton("Cancel");
		
		getEmployeeID();
		selectEmployeeIdLabel.setBounds(30, 40, 150, 30);
		employeeNameLabel.setBounds(30, 90, 150, 30);
		addressLabel.setBounds(30, 140, 150, 30);
		contactNoLabel.setBounds(30, 190, 150, 30);
		emailIDLabel.setBounds(30, 240, 150, 30);
		
		selectEmployeeIDComboBox.setBounds(220, 40, 200, 30);
		employeeNameDetails.setBounds(220, 90, 200, 30);
		addressDetails.setBounds(220, 140, 200, 30);
		contactNoDetails.setBounds(220, 190, 200, 30);
		emailIDDetails.setBounds(220, 240, 200, 30);
		deleteButton.setBounds(40, 300, 150, 30);
		cancelButton.setBounds(220, 300, 150, 30);
		
		deleteEmployeeFrame.add(selectEmployeeIdLabel);
		deleteEmployeeFrame.add(employeeNameLabel);
		deleteEmployeeFrame.add(addressLabel);
		deleteEmployeeFrame.add(contactNoLabel);
		deleteEmployeeFrame.add(emailIDLabel);
		deleteEmployeeFrame.add(employeeNameDetails);
		deleteEmployeeFrame.add(addressDetails);
		deleteEmployeeFrame.add(contactNoDetails);
		deleteEmployeeFrame.add(emailIDDetails);
		deleteEmployeeFrame.add(selectEmployeeIDComboBox);
		deleteEmployeeFrame.add(deleteButton);
		deleteEmployeeFrame.add(cancelButton);
		
		addressDetails.setBackground(EmployeeHomePage.myblue);
		contactNoDetails.setBackground(EmployeeHomePage.myblue);
		emailIDDetails.setBackground(EmployeeHomePage.myblue);
		employeeNameDetails.setBackground(EmployeeHomePage.myblue);
		addressDetails.setForeground(Color.WHITE);
		contactNoDetails.setForeground(Color.WHITE);
		emailIDDetails.setForeground(Color.WHITE);
		employeeNameDetails.setForeground(Color.WHITE);
		addressDetails.setOpaque(true);
		contactNoDetails.setOpaque(true);
		emailIDDetails.setOpaque(true);
		employeeNameDetails.setOpaque(true);
		
		deleteEmployeeFrame.setLayout(null);
		deleteEmployeeFrame.setSize(450, 420);
		deleteEmployeeFrame.setLocation((1366-450)/2, (768-420)/2);
		deleteEmployeeFrame.setVisible(true);
		deleteEmployeeFrame.setResizable(false);
		deleteEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(selectEmployeeIDComboBox.getSelectedIndex()>0) {
					if(deleteRecord()) {
						JOptionPane.showMessageDialog(deleteEmployeeFrame, "Employee Deleted");
						deleteEmployeeFrame.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(deleteEmployeeFrame, "Error",
						"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(deleteEmployeeFrame, 
					"Please Select a Employee ID ", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteEmployeeFrame.setVisible(false);
				
			}
		});

		selectEmployeeIDComboBox.addItemListener(new ItemListener() {

		
			public void itemStateChanged(ItemEvent arg0) {
				
				if(selectEmployeeIDComboBox.getSelectedItem()!="") {
					initializeDetails();
				}
				else {
					clearLabels();
				}
			}
			
		});
	}//end of constructor

	private void clearLabels() {
		employeeNameDetails.setText("");
		addressDetails.setText("");
		contactNoDetails.setText("");
		emailIDDetails.setText("");
	}
	
	protected void setInfoLabels() {
		
		clearLabels();
		employeeNameDetails.setText(employeename);
		addressDetails.setText(address);
		contactNoDetails.setText(""+contactno);
		emailIDDetails.setText(emailid);
	}
	
	protected void initializeDetails() {
		
		employeeid= Integer.parseInt((String) selectEmployeeIDComboBox.getSelectedItem());
		try{
			java.sql.Statement smt = Connect.con.createStatement();
			
			ResultSet rs = smt.executeQuery("select empname, address, emailid, mobileno" +
			" from inventory_emp where empid="+employeeid);
	
			while(rs.next()) {
				employeename = rs.getString("empname");
				address = rs.getString("address");
				contactno = rs.getLong("mobileno");
				emailid = rs.getString("emailid");
			}
			setInfoLabels();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

        
        
        
	protected boolean deleteRecord() {
		
		try{
			PreparedStatement psmt = Connect.con.prepareStatement("delete from inventory_emp where empid= ?");
			psmt.setInt(1, employeeid);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}//end of deleteRecord()
	
        
        
	public void getEmployeeID() {
	
		selectEmployeeIDComboBox.removeAllItems();
		selectEmployeeIDComboBox.addItem("");
		try {
			Statement smt = Connect.con.createStatement();
			ResultSet rs = smt.executeQuery("select empid from inventory_emp");
			while(rs.next()) {
				selectEmployeeIDComboBox.addItem(rs.getString("empid"));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
        
        public static void main(String[] args){
            new DeleteEmployee();
        }
        
}


