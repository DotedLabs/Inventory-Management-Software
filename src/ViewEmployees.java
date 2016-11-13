

import java.awt.*;
import java.io.File;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class ViewEmployees {

	JFrame employeeDetailsFrame;
	JTable employeeTable;
	JScrollPane tableContainer;
	ArrayList<String[]> ar;
	ViewEmployees() {
            
            
		//Calculating WOrk Duration of the Employee
                String plsql = "declare\n" +
                               "begin\n" +
                               "calculateWork;\n" +
                               "end;";
                
                
                try{
                    Connect.getConnection();
                    PreparedStatement pst = Connect.con.prepareStatement(plsql);
                    pst.execute();
                }
                
                catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }
                
                employeeDetailsFrame = new JFrame("Employee Details");
                File currentFolder = new File(System.getProperty("user.dir"));  
                ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
                employeeDetailsFrame.setIconImage(icon.getImage());
		employeeDetailsFrame.setLayout(new FlowLayout()); 
		

                //connect to database
		String columns[] = {"Employee ID", "Name", "Password", "Date Of Join", "Address", "Email ID", "Contact No"," Work Duration(in Minutes)"};
		getData();
		
		String data[][]=new String[ar.size()][8];               
		for(int i = 0; i<ar.size(); i++) {
    		data[i]= ar.get(i);	
    	}
    	
		if(data[0]==null) {
			JOptionPane.showMessageDialog(null, "Unable to Extract Employee Table",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		else {
                    try{
		    employeeTable = new JTable(data, columns);
		    employeeTable.setPreferredScrollableViewportSize(new Dimension(950, 200));
		    employeeTable.setFillsViewportHeight(true);
		    
		    tableContainer = new JScrollPane(employeeTable);
			employeeDetailsFrame.getContentPane().add(tableContainer, BorderLayout.CENTER);
			
		    employeeDetailsFrame.setSize(600, 600);
		    employeeDetailsFrame.setLocation((1366-600)/2, (768-600)/2);
		    employeeDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    
		    employeeDetailsFrame.pack();
		    employeeDetailsFrame.setVisible(true);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void getData() {
		ar = new ArrayList<String[]>();
		try{
			Statement smt = Connect.con.createStatement();
			ResultSet rs = smt.executeQuery("select * from inventory_emp order by empid");
			String[] allData;
			while(rs.next()) {
				allData = new String[8];
				allData[0]=rs.getString("empid");
				allData[1]=rs.getString("empname");
				allData[2]=rs.getString("password");
				allData[3]=rs.getString("date_join");
				allData[4]=rs.getString("address");
				allData[5]=rs.getString("emailid");
                                allData[6]=rs.getString("mobileno");
                                allData[7]=String.valueOf(rs.getInt("totalwork"));
				ar.add(allData);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
        
        public static void main(String[] args){
            new ViewEmployees();
            
        }
}