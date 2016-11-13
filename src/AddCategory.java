
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.*;
import javax.swing.*;


public class AddCategory extends javax.swing.JFrame {
    
    //Main Panel and it's components
    JTextField priceTextField,categoryTextField,idTextField, nameTextField,quantityTextField;
    JButton addButton,closeButton;
    JLabel productNameLabel,productIDLabel,categoryLabel,quantityLabel,priceLabel;
    JPanel mainPanel;

    //warning labels for validation
    JLabel idWarning;
    JLabel nameWarning;
    JLabel categoryWarning;
    JLabel quantityWarning;
    JLabel priceWarning;
    
    


    public AddCategory() {
       
        super("Add Product to New Category");
        Connect.getConnection();
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());  
        initComponents();
    }

    private void initComponents() {

        mainPanel = new JPanel();
        
        productNameLabel = new JLabel();
        productIDLabel = new JLabel();
        categoryLabel = new JLabel();
        quantityLabel = new JLabel();
        priceLabel = new JLabel();
        
        nameTextField = new JTextField();
        idTextField = new JTextField();
        categoryTextField = new JTextField();
        priceTextField = new JTextField();
        quantityTextField = new JTextField();
        
        addButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);

        productNameLabel.setFont(new Font("Calibri", 1, 20)); 
        productNameLabel.setText("Product Name");
        mainPanel.add(productNameLabel);
        productNameLabel.setBounds(70, 49, 130, 24);
        
        nameWarning = new javax.swing.JLabel();
        nameWarning.setText("");
        nameWarning.setBounds(222, 75, 300, 20);
        nameWarning.setForeground(Color.RED);
        mainPanel.add(nameWarning);

        productIDLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        productIDLabel.setText("Product ID");
        mainPanel.add(productIDLabel);
        productIDLabel.setBounds(70, 131, 111, 22);
        
        idWarning = new javax.swing.JLabel();
        idWarning.setText("");
        idWarning.setBounds(222, 152, 300, 20);
        idWarning.setForeground(Color.RED);
        mainPanel.add(idWarning);
        

        categoryLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        categoryLabel.setText("Category");
        mainPanel.add(categoryLabel);
        categoryLabel.setBounds(70, 200, 180, 22);
        
        categoryWarning = new javax.swing.JLabel();
        categoryWarning.setText("");
        categoryWarning.setBounds(222, 222, 300, 20);
        categoryWarning.setForeground(Color.RED);
        mainPanel.add(categoryWarning);

        quantityLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        quantityLabel.setText("Quantity");
        mainPanel.add(quantityLabel);
        quantityLabel.setBounds(70, 286, 140, 22);
        
        quantityWarning = new javax.swing.JLabel();
        quantityWarning.setText("");
        quantityWarning.setBounds(222, 305, 300, 20);
        quantityWarning.setForeground(Color.RED);
        mainPanel.add(quantityWarning);

        priceLabel.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        priceLabel.setText("Unit Price");
        mainPanel.add(priceLabel);
        priceLabel.setBounds(70, 358, 130, 30);
        
        priceWarning = new javax.swing.JLabel();
        priceWarning.setText("");
        priceWarning.setBounds(222, 384, 300, 20);
        priceWarning.setForeground(Color.RED);
        mainPanel.add(priceWarning);
        
        mainPanel.add(nameTextField);
        nameTextField.setBounds(222, 45, 191, 32);

        mainPanel.add(idTextField);
        idTextField.setBounds(222, 121, 191, 32);
        
        mainPanel.add(categoryTextField);
        categoryTextField.setBounds(222, 192, 191, 32);

        mainPanel.add(priceTextField);
        priceTextField.setBounds(222, 353, 191, 32);
        mainPanel.add(quantityTextField);
        quantityTextField.setBounds(222, 276, 191, 32);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addButton);
        addButton.setBounds(460, 276, 80, 32);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        mainPanel.add(closeButton);
        closeButton.setBounds(460, 353, 80, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );

        pack();
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        
        clearWarningLabels();
        
        String productId = idTextField.getText();
        String productName = nameTextField.getText();
        String productCategory = categoryTextField.getText();
        String productQuantity = quantityTextField.getText();
        String productPrice = priceTextField.getText();
        boolean warning = false;
        //Validation of all TextFields
        try{
            if(productId.isEmpty()){
                //Warning
                idWarning.setText(" *Product ID must not be empty.");     
                warning = true;
            }
            if(productName.isEmpty()){
                nameWarning.setText(" *Product Name must not be empty.");     
                warning = true;
            }
            if(productCategory.isEmpty()){
                categoryWarning.setText(" *Product Category must not be empty.");     
                warning = true;
            }
            
            if(productQuantity.isEmpty()){
                quantityWarning.setText(" *Product Quantity must not be empty.");     
                warning = true;
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
                //After Validating all the fileds if warning not found, then add to table
                PreparedStatement pst=null;
                ResultSet rs=null;
                Statement st=null;
                // First check if the product has been already there in the inventory list or not
                try {
                    String sqlGetPid = "select PRODUCT_ID from PRODUCT_TABLE where PRODUCT_ID = '"+productId+"'  ";
                    st = Connect.con.createStatement();
                    rs = st.executeQuery(sqlGetPid);

                    if(rs.next()){
                        //If product is already there in the list just update the stock
                        /*System.out.println("Product Already Exists!");
                        String updateQuery = " update product_table set product_quantity = product_quantity + '"+productQuantity+"' where PRODUCT_ID =  '"+productId+"' ";
                        pst = Connect.con.prepareStatement(updateQuery);
                        pst.execute();*/

                        JOptionPane.showMessageDialog(null, "Product already Exists! Please go to Add Stock window!");
                    }
                    else{
                        System.out.println("New Product Arrived!");
                        String sql = "insert into Product_table values(?,?,?,?,?)";
                        pst= Connect.con.prepareStatement(sql);
                        pst.setString(1,productId);
                        pst.setString(2,productName);
                        pst.setString(3,productCategory);
                        pst.setInt(4,Integer.parseInt(productQuantity));
                        pst.setFloat(5,Float.parseFloat(productPrice));
                        pst.execute();
                        JOptionPane.showMessageDialog(null, "New Product & New Category Added");
                    }

                } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null,ex);
                }

                WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
                }
            
            
            
    }catch(ProductException pe){
           //JOptionPane.showMessageDialog(null,pe); 
           pe.printStackTrace();
    }

    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed        
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 

    }
    
    private void clearWarningLabels() {
       idWarning.setText("");
       nameWarning.setText("");
       categoryWarning.setText("");
       quantityWarning.setText("");
       priceWarning.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCategory().setVisible(true);
            }
        });
    }

}

