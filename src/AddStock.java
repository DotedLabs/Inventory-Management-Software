
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public class AddStock extends JFrame {
    
        
    private JTextField priceTextField;
    private JTextField idTextField;
    private JTextField quantityTextField;
    
    private JButton addButton;
    private JButton closeButton;
    
    private JLabel categoryLabel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel jLabel6;
    private JPanel mainPanel;
    private JComboBox<String> category;
    private JComboBox<String> product;
    
    //JLabel idWarning;
    JLabel nameWarning;
    JLabel categoryWarning;
    JLabel quantityWarning;
    JLabel priceWarning;

    public AddStock() {
        super("Add Stock");
        Connect.getConnection();    
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());

        initComponents();
    }


    private void initComponents() {

        mainPanel = new JPanel();
        
        categoryLabel = new JLabel();
        nameLabel = new JLabel();
        idLabel = new JLabel();
        priceLabel = new JLabel();
        quantityLabel = new JLabel();
        
        category = new JComboBox<String> ();
        product = new JComboBox<String> ();
        
        idTextField = new JTextField();
        priceTextField = new JTextField();
        quantityTextField = new JTextField();
        
        addButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);

        categoryLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        categoryLabel.setText("Category");
        mainPanel.add(categoryLabel);
        categoryLabel.setBounds(70, 40, 111, 24);
        
        categoryWarning = new JLabel();
        categoryWarning.setText("");
        categoryWarning.setBounds(222, 65, 300, 20);
        categoryWarning.setForeground(Color.RED);
        mainPanel.add(categoryWarning);

        nameLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        nameLabel.setText("Product Name");
        mainPanel.add(nameLabel);
        nameLabel.setBounds(70, 120, 130, 30);
        
        nameWarning = new JLabel();
        nameWarning.setText("");
        nameWarning.setBounds(222, 151, 300, 20);
        nameWarning.setForeground(Color.RED);
        mainPanel.add(nameWarning);

        idLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        idLabel.setText("Product ID");
        mainPanel.add(idLabel);
        idLabel.setBounds(70, 195, 180, 24);
        
        //Product id will not be changed
        /*idWarning = new JLabel();
        idWarning.setText("");
        idWarning.setBounds(222, 152, 300, 20);
        idWarning.setForeground(Color.RED);
        mainPanel.add(idWarning);*/

        priceLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        priceLabel.setText("Unit Price");
        mainPanel.add(priceLabel);
        priceLabel.setBounds(70, 276, 140, 24);
        
        priceWarning = new JLabel();
        priceWarning.setText("");
        priceWarning.setBounds(222, 305, 300, 20);
        priceWarning.setForeground(Color.RED);
        mainPanel.add(priceWarning);

        quantityLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        quantityLabel.setText("Quantity");
        mainPanel.add(quantityLabel);
        quantityLabel.setBounds(70, 350, 130, 30);
        categoryPool();
        mainPanel.add(category);
        category.setBounds(222, 30, 191, 32);
        
        quantityWarning = new JLabel();
        quantityWarning.setText("");
        quantityWarning.setBounds(222, 384, 300, 20);
        quantityWarning.setForeground(Color.RED);
        mainPanel.add(quantityWarning);
        
        
        mainPanel.add(product);
        product.setBounds(222, 121, 191, 32);
        
        mainPanel.add(idTextField);
        idTextField.setBounds(222, 192, 191, 32);
        
        mainPanel.add(priceTextField);
        priceTextField.setBounds(222, 276, 191, 32);
        
        mainPanel.add(quantityTextField);
        quantityTextField.setBounds(222,353,191,32);


        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addButton);
        addButton.setBounds(460, 276, 80, 32);

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
        
        category.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent arg0) {
               if(category.getSelectedItem() != ""){
                   populateProducts();
               }else{
                   System.out.println("\n Nothing selected");
                   //clearLabels();
               }
               
           }
        });
        
        product.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent arg0) {
               if(product.getSelectedItem() != ""){
                  initializeProduct();
               }else{
                   System.out.println("\n Nothing selected");
                   //clearLabels();
               }
               
           }
        });

        pack();
    }

    private void addButtonActionPerformed(ActionEvent evt) {

        clearWarningLabels();
        
        String productId = idTextField.getText();
        String productName = (String) product.getSelectedItem();
        String productCategory = (String) category.getSelectedItem();
        String productQuantity = quantityTextField.getText();
        String productPrice = priceTextField.getText();
        boolean warning = false;
        //Validation of all TextFields
        try{
            /*if(productId.isEmpty()){
                //Warning
                idWarning.setText(" *Product ID must not be empty.");     
                warning = true;
            }*/
            if(productCategory.isEmpty()){
                categoryWarning.setText(" *Please select one category.");     
                warning = true;
            }
            if(productName.isEmpty()){
                nameWarning.setText(" *Please select one product.");     
                warning = true;
            }
            
            if(productQuantity.isEmpty()){
                quantityWarning.setText(" *Product priceTextField will be changed.");     
                //warning = true;
            }
            if(!productQuantity.isEmpty() && Integer.parseInt(productQuantity)<0){
                quantityWarning.setText(" *Product Quantity must be positive value.");     
                warning = true;
            }
            
            if(productPrice.isEmpty()){
                priceWarning.setText(" *Product Price must not be empty.");     
                warning = true;
            }
            
            if(!productPrice.isEmpty() && Float.parseFloat(productPrice)<0){
                priceWarning.setText(" *Product Price must be positive value.");     
                warning = true;
            }
            
            if(warning){
                throw new ProductException(" Data Entry Error!");
            }else{
        
                PreparedStatement pst=null;
                ResultSet rs=null;
                Statement st=null;

                try {
                if(productQuantity.isEmpty()){
                    //System.out.println("\n Only price of the product has been changed");
                    String updateQuery = " update product_table set price = '"+productPrice+"' where PRODUCT_ID =  '"+productId+"' ";
                    pst = Connect.con.prepareStatement(updateQuery);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Price of the product updated");
                }
                else{
                    String updateQuery = " update product_table set product_quantity = product_quantity + '"+productQuantity+"' , price = '"+productPrice+"' where PRODUCT_ID =  '"+productId+"' ";
                    pst = Connect.con.prepareStatement(updateQuery);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "New Stock Arrived & Price updated");
                }
                
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,ex);
                }

                WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
            }
        }catch(ProductException pe){
            pe.printStackTrace();
        }

    }

    private void closeButtonActionPerformed(ActionEvent evt) {  
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 
    }
    
    private void clearWarningLabels() {
       //idWarning.setText("");
       nameWarning.setText("");
       categoryWarning.setText("");
       quantityWarning.setText("");
       priceWarning.setText("");
    }
    
    private void categoryPool(){
        category.removeAllItems();
        category.addItem("");
        category.addItem("All Category");
        try{
            Connect.getConnection();
            Statement smt = Connect.con.createStatement();
            ResultSet rs = smt.executeQuery("select distinct product_category from product_table order by product_category");
            
            while(rs.next()){
                System.out.println("  "+rs.getString("product_category"));
                category.addItem(rs.getString("product_category"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
     private void populateProducts(){
        
        String cat = (String) category.getSelectedItem();
        ResultSet rs = null;
        try{
            product.removeAllItems();
            Connect.getConnection();
            
            java.sql.Statement smt = Connect.con.createStatement();
            
            if(cat == "All Category"){
                rs = smt.executeQuery("select PRODUCT_NAME from PRODUCT_TABLE order by product_name");
            } else{
                rs = smt.executeQuery("select PRODUCT_NAME from PRODUCT_TABLE where PRODUCT_CATEGORY = '"+cat+"' order by product_name  ");
            }
            
            product.addItem("");
            while(rs.next()){
                product.addItem(rs.getString("product_name"));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     
     private void initializeProduct(){
         clearWarningLabels();
         String productName = (String) product.getSelectedItem();
        try{
            //product.removeAllItems();
            Connect.getConnection();
            
            java.sql.Statement smt = Connect.con.createStatement();
            
            ResultSet rs = smt.executeQuery("select product_id,price from PRODUCT_TABLE where PRODUCT_NAME = '"+productName+"'  ");
            
            if(rs.next()){
                idTextField.setText(rs.getString("product_id"));
                priceTextField.setText(rs.getString("price"));
                idTextField.setEditable(false);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
     }


    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddStock().setVisible(true);
            }
        });
    }

   
}

