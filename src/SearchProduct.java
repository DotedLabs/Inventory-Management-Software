import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class SearchProduct extends JFrame {
    
    private JTextField priceTextField;
    private JTextField idTextField;
    private JTextField productQuantityTextField;
    private JButton closeButton;
    private JLabel categoryLabel;
    private JLabel productNameLabel;
    private JLabel productIdLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JPanel mainPanel;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> productComboBox;

    public SearchProduct() {
        super("Search Product");
        Connect.getConnection();
        
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage()); 
        
        initComponents();
    }


    private void initComponents() {

        mainPanel = new JPanel();
        
        categoryLabel = new JLabel();
        productNameLabel = new JLabel();
        productIdLabel = new JLabel();
        priceLabel = new JLabel();
        quantityLabel = new JLabel();
        
        categoryComboBox = new JComboBox<String> ();
        productComboBox = new JComboBox<String> ();
        
        idTextField = new JTextField();
        priceTextField = new JTextField();
        productQuantityTextField = new JTextField();
        
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);

        categoryLabel.setFont(new Font("Calibri", 1, 18));
        categoryLabel.setText("Category");
        mainPanel.add(categoryLabel);
        categoryLabel.setBounds(70, 40, 111, 24);

        productNameLabel.setFont(new Font("Calibri", 1, 18));
        productNameLabel.setText("Product Name");
        mainPanel.add(productNameLabel);
        productNameLabel.setBounds(70, 120, 111, 22);

        productIdLabel.setFont(new Font("Calibri", 1, 18));
        productIdLabel.setText("Product ID");
        mainPanel.add(productIdLabel);
        productIdLabel.setBounds(70, 190, 180, 22);

        priceLabel.setFont(new Font("Calibri", 1, 18));
        priceLabel.setText("Unit Price");
        mainPanel.add(priceLabel);
        priceLabel.setBounds(70, 276, 140, 22);

        quantityLabel.setFont(new Font("Calibri", 3, 18));
        quantityLabel.setText("Quantity");
        mainPanel.add(quantityLabel);
        quantityLabel.setBounds(70, 350, 130, 30);
        

        categoryPool(); // to generate category pool
        mainPanel.add(categoryComboBox);
        categoryComboBox.setBounds(222, 30, 191, 32);
        
        
        mainPanel.add(productComboBox);
        productComboBox.setBounds(222, 121, 191, 32);
        
        mainPanel.add(idTextField);
        idTextField.setBounds(222, 192, 191, 32);
        
        mainPanel.add(priceTextField);
        priceTextField.setBounds(222, 276, 191, 32);
        
        mainPanel.add(productQuantityTextField);
        productQuantityTextField.setBounds(222,353,191,32);

        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        mainPanel.add(closeButton);
        closeButton.setBounds(460, 353, 80, 32);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        
        categoryComboBox.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent arg0) {
               if(categoryComboBox.getSelectedItem() != ""){
                   populateProducts();
               }else{
                   System.out.println("\n Nothing selected");
               }
               
           }
        });
        
        productComboBox.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent arg0) {
               if(productComboBox.getSelectedItem() != ""){
                  initializeProduct();
               }else{
                   System.out.println("\n Nothing selected");
               }
               
           }
        });

        pack();
    }

    private void closeButtonActionPerformed(ActionEvent evt) {
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 

    }
    
    private void categoryPool(){
        categoryComboBox.removeAllItems();
        categoryComboBox.addItem("");
        categoryComboBox.addItem("All Category");
        try{
            Statement smt = Connect.con.createStatement();
            ResultSet rs = smt.executeQuery("select distinct product_category from product_table order by product_category");
            
            while(rs.next()){
                System.out.println("  "+rs.getString("product_category"));
                categoryComboBox.addItem(rs.getString("product_category"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
     private void populateProducts(){
        String cat = (String) categoryComboBox.getSelectedItem();
        ResultSet rs = null;
        try{
            productComboBox.removeAllItems();
            Statement smt = Connect.con.createStatement();
            
            if(cat == "All Category"){
                rs = smt.executeQuery("select PRODUCT_NAME from PRODUCT_TABLE order by product_name");
            } else{
                rs = smt.executeQuery("select PRODUCT_NAME from PRODUCT_TABLE where PRODUCT_CATEGORY = '"+cat+"' order by product_name  ");
            }
            
            productComboBox.addItem("");
            while(rs.next()){
                productComboBox.addItem(rs.getString("product_name"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
     private void initializeProduct(){
         String productName = (String) productComboBox.getSelectedItem();
        try{
            //productComboBox.removeAllItems();
            java.sql.Statement smt = Connect.con.createStatement();
            
            ResultSet rs = smt.executeQuery("select product_id,price,product_quantity from PRODUCT_TABLE where PRODUCT_NAME = '"+productName+"'  ");
            
            if(rs.next()){
                idTextField.setText(rs.getString("product_id"));
                priceTextField.setText(rs.getString("price"));
                productQuantityTextField.setText(rs.getString("product_quantity"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
     }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchProduct().setVisible(true);
            }
        });
    }
   
}

