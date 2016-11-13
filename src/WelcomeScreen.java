
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.Statement;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;

class WelcomeScreen {

	JFrame marketBasketFrame,managerFrame,employeeFrame;
	JButton managerButton,employeeButton,exitButton,managerLoginButton,managerBackButton,emplyoeeLoginButton,emplyoeeBackButton;
	JLabel managerLoginLabel,managerPasswordLabel,employeeLoginLabel,employeePasswordLabel;
	JTextField managerLoginTextField,employeeLoginTextField;
	JPasswordField managerPasswordFeild,employeePasswordField;
	
	
	final static Font allButton=new Font("Calibri",Font.PLAIN,18);
	final static Font allLabel=new Font("Calibri",Font.PLAIN,18);
	
   static BufferedImage myImage;
   
   
   WelcomeScreen(){
	   
	   marketBasketFrame=new JFrame("Market Basket");
	   managerFrame=new JFrame("Login as Manager");
	   employeeFrame=new JFrame("Login as Employee");
	  
           marketBasketFrame.setBackground(Color.WHITE);
           
           File currentFolder = new File(System.getProperty("user.dir"));  
           ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
           
           marketBasketFrame.setIconImage(icon.getImage());
           managerFrame.setIconImage(icon.getImage());
           employeeFrame.setIconImage(icon.getImage());
	   
	   Connect.getConnection();
	   
	 
	   
	   managerLoginLabel=new JLabel("Enter Login ID:");
	   managerPasswordLabel=new JLabel("Enter Password");
	   employeeLoginLabel=new JLabel("Enter Login ID:");
	   employeePasswordLabel=new JLabel("Enter Password");
	   
	   
	   managerButton=new JButton("Manager");
	   employeeButton=new JButton("Employee");
	   exitButton=new JButton("Exit");
	   managerLoginButton=new JButton("LogIn");
	   managerBackButton=new JButton("Back");
	   emplyoeeLoginButton=new JButton("LogIn");
	   emplyoeeBackButton=new JButton("Back");
	   
	   
	   managerButton.setFont(allButton);
	   employeeButton.setFont(allButton);
	   exitButton.setFont(allButton);
	   managerLoginButton.setFont(allButton);
	   managerBackButton.setFont(allButton);
	   emplyoeeLoginButton.setFont(allButton);
	   emplyoeeBackButton.setFont(allButton);
	   
	   
	   
	   
	   managerLoginLabel.setFont(allLabel);
	   managerPasswordLabel.setFont(allLabel);
	   employeeLoginLabel.setFont(allLabel);
	   employeePasswordLabel.setFont(allLabel);
	   
	   
	   managerLoginTextField=new JTextField();
	   employeeLoginTextField=new JTextField();
	   managerPasswordFeild=new JPasswordField();
	   employeePasswordField=new JPasswordField();
	   
	   
	   managerButton.setBounds(100,400,150,30);
	   employeeButton.setBounds(300,400,150,30);
	   exitButton.setBounds(500,400,150,30);
	   managerLoginButton.setBounds(70,200,130,40);
	   managerBackButton.setBounds(250,200,130,40);
	   emplyoeeLoginButton.setBounds(70,200,130,40);
	   emplyoeeBackButton.setBounds(250,200,130,40);
	   
	   managerLoginLabel.setBounds(40,50,140,40);
	   managerPasswordLabel.setBounds(40,120,140,40);
	   employeeLoginLabel.setBounds(40,50,140,40);
	   employeePasswordLabel.setBounds(40,120,140,40);
	   
	   managerLoginTextField.setBounds(200,50,200,30);
	   employeeLoginTextField.setBounds(200,50,200,30);
	   
	   
	   managerPasswordFeild.setBounds(200,120,200,30);
	   employeePasswordField.setBounds(200,120,200,30);
	   
	   
	  try{
              
             
                myImage=ImageIO.read(new File(currentFolder.getPath().concat("/images/logoMain.jpg")));
	   }
	   
	   catch(IOException e){
		   e.printStackTrace();
	   }
	   
	   
	  marketBasketFrame.setContentPane(new ImagePanel(myImage)); 
	   
	   marketBasketFrame.add(managerButton);  
	   marketBasketFrame.add(employeeButton); 
	   marketBasketFrame.add(exitButton);  
	   
	   
	   
	   managerFrame.add(managerLoginLabel); 
           managerFrame.add(managerPasswordLabel);  
	   
	   
	   managerFrame.add(managerLoginTextField); 
	   managerFrame.add(managerPasswordFeild); 	   
	   
	   managerFrame.add(managerLoginButton);
	   managerFrame.add(managerBackButton);
	   
	   
	   employeeFrame.add(employeeLoginLabel);
	   employeeFrame.add(employeePasswordLabel);
	   
	   employeeFrame.add(employeeLoginTextField);
	   employeeFrame.add(employeePasswordField);
	   
	   
	   employeeFrame.add(emplyoeeLoginButton);
	   employeeFrame.add(emplyoeeBackButton);
	   
	   
	   marketBasketFrame.setDefaultCloseOperation(marketBasketFrame.EXIT_ON_CLOSE);
	   managerFrame.setDefaultCloseOperation(marketBasketFrame.DISPOSE_ON_CLOSE);
	   employeeFrame.setDefaultCloseOperation(marketBasketFrame.DISPOSE_ON_CLOSE);
	   
	   marketBasketFrame.setSize(770,495);
	   marketBasketFrame.setLayout(null);
	   
	   marketBasketFrame.setLocation((1366-770)/2,(768-495)/2);
	   marketBasketFrame.setVisible(true);
	   marketBasketFrame.setResizable(false);
	   
	   
	   managerFrame.setSize(450,320);
	   managerFrame.setLocation((1366-450)/2,(768-320)/2);
	   managerFrame.setLayout(null);
	   managerFrame.setResizable(false);
	   managerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   
	   
	   employeeFrame.setSize(450,320);
	   employeeFrame.setLocation((1366-450)/2,(768-320)/2);
	   employeeFrame.setLayout(null);
	   employeeFrame.setResizable(false);
	   employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	   
	   
	 
	   managerButton.addActionListener(new ActionListener(){
		   
		   @Override
		   public void actionPerformed(ActionEvent ae){
			   managerLoginTextField.setText("");
			   managerPasswordFeild.setText("");
			   managerFrame.setVisible(true);
		   }
	   });
	   
	   
	
	   employeeButton.addActionListener(new ActionListener(){
		   
		   @Override
		   public void actionPerformed(ActionEvent ae){
			   employeeLoginTextField.setText("");
			   employeePasswordField.setText("");
			   employeeFrame.setVisible(true);
		   }
	   });
	   
	  
	   
	 
	   exitButton.addActionListener(new ActionListener(){
		   
		   public void actionPerformed(ActionEvent ae){
			   marketBasketFrame.setVisible(false);
			   System.exit(0);
			   
		   }
	   });
	   
	   
	   
	  
	   managerLoginButton.addActionListener(new ActionListener(){ 
		   public void actionPerformed(ActionEvent ae){
			   
			   if(loginAdmin()) {
					managerFrame.setVisible(false);
					marketBasketFrame.setVisible(false);
                                        System.out.println("\n After loginAdmin Called");
					new AdminHomePage(managerLoginTextField.getText());
                                        
				}else {
					JOptionPane.showMessageDialog(managerFrame,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}   
		   
	   });
	   
	   
	   
	   
	   managerBackButton.addActionListener(new ActionListener(){
		   
		  public void actionPerformed(ActionEvent ae){
			  managerFrame.setVisible(false);
			  marketBasketFrame.setVisible(true);
			  
		  }
	   });
	   
	   

   
	   
	   
      emplyoeeLoginButton.addActionListener(new ActionListener(){
    	
    	  public void actionPerformed(ActionEvent ae){
    		
    		  
    		  if(loginEmployee()) {
					employeeFrame.setVisible(false);
					marketBasketFrame.setVisible(false);
					new EmployeeHomePage(employeeLoginTextField.getText());
				}else {
					JOptionPane.showMessageDialog(employeeFrame,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}  
    	  }
    	  
      });
	   emplyoeeBackButton.addActionListener(new ActionListener(){
		   
		  public void actionPerformed(ActionEvent ae){
			  employeeFrame.setVisible(false);
			  marketBasketFrame.setVisible(true);
			  
		  }
	   }); 
           
           
           managerPasswordFeild.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent ae){
                     if(loginAdmin()) {
					managerFrame.setVisible(false);
					marketBasketFrame.setVisible(false);
                                        System.out.println("\n After loginAdmin Called");
					new AdminHomePage(managerLoginTextField.getText());
                                        
				}else {
					JOptionPane.showMessageDialog(managerFrame,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}   
		   
                  
              
           });
           
           
           
           employeePasswordField.addActionListener(new ActionListener(){
               
               public void actionPerformed(ActionEvent ae){
                     if(loginEmployee()) {
					employeeFrame.setVisible(false);
					marketBasketFrame.setVisible(false);
					new EmployeeHomePage(employeeLoginTextField.getText());
				}else {
					JOptionPane.showMessageDialog(employeeFrame,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}
                     
    	                }
           });
           
           
	   
   }//end of constructor

	
	protected boolean loginAdmin() {
		
		java.sql.Statement smt = null;
		ResultSet rs = null;
		try {
			smt = Connect.con.createStatement();
			rs = smt.executeQuery("select password from inventory_manager where managerid='"+managerLoginTextField.getText().trim()+"'");
			String pass = null;
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(managerPasswordFeild.getText().trim())){
                            System.out.println("\n Validated from login Admin()");
                            return true;
                        }
				
			else 
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	protected boolean loginEmployee() {
		
		java.sql.Statement smt = null;
		ResultSet rs = null;
		try {
			smt = Connect.con.createStatement();
			rs = smt.executeQuery("select password from inventory_emp where empid='"+employeeLoginTextField.getText().trim()+"'");
			String pass = null;
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(employeePasswordField.getText()))
				return true;
			else 
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
          
public static void main(String a[]){
	new WelcomeScreen();
}
   
   
}




	
	
	
	



