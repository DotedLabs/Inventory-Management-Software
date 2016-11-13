
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

public class AddProduct extends JFrame {
    
    private JTextField priceTextField;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JButton addButton;
    private JButton closeButton;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel categoryLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;
    private JPanel mainPanel;
    private JComboBox<String> categoryComboBox;
    
    //warning labels for validation
    JLabel idWarning;
    JLabel nameWarning;
    JLabel categoryWarning;
    JLabel quantityWarning;
    JLabel priceWarning;

    public AddProduct() {
        super("Add Product to Existing Category");
        Connect.getConnection();
        
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());

        initComponents();
    }


    private void initComponents() {

        mainPanel = new JPanel();
        
        nameLabel = new JLabel();
        idLabel = new JLabel();
        categoryLabel = new JLabel();
        quantityLabel = new JLabel();
        priceLabel = new JLabel();
        
        categoryComboBox = new JComboBox<String> ();
        
        nameTextField = new JTextField();
        idTextField = new JTextField();
        priceTextField = new JTextField();
        quantityTextField = new JTextField();
        
        addButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);

        nameLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        nameLabel.setText("Product Name");
        mainPanel.add(nameLabel);
        nameLabel.setBounds(70, 49, 130, 24);
        
        nameWarning = new JLabel();
        nameWarning.setText("");
        nameWarning.setBounds(222, 75, 300, 20);
        nameWarning.setForeground(Color.RED);
        mainPanel.add(nameWarning);

        idLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        idLabel.setText("Product ID");
        mainPanel.add(idLabel);
        idLabel.setBounds(70, 131, 111, 22);
        
        idWarning = new JLabel();
        idWarning.setText("");
        idWarning.setBounds(222, 152, 300, 20);
        idWarning.setForeground(Color.RED);
        mainPanel.add(idWarning);

        categoryLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        categoryLabel.setText("Category");
        mainPanel.add(categoryLabel);
        categoryLabel.setBounds(70, 200, 180, 22);
        
        categoryWarning = new JLabel();
        categoryWarning.setText("");
        categoryWarning.setBounds(222, 222, 300, 20);
        categoryWarning.setForeground(Color.RED);
        mainPanel.add(categoryWarning);

        quantityLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        quantityLabel.setText("Quantity");
        mainPanel.add(quantityLabel);
        quantityLabel.setBounds(70, 286, 140, 22);
        
        quantityWarning = new JLabel();
        quantityWarning.setText("");
        quantityWarning.setBounds(222, 305, 300, 20);
        quantityWarning.setForeground(Color.RED);
        mainPanel.add(quantityWarning);

        priceLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        priceLabel.setText("Unit Price");
        mainPanel.add(priceLabel);
        priceLabel.setBounds(70, 358, 130, 30);
        
        priceWarning = new JLabel();
        priceWarning.setText("");
        priceWarning.setBounds(222, 384, 300, 20);
        priceWarning.setForeground(Color.RED);
        mainPanel.add(priceWarning);
        
        
        mainPanel.add(nameTextField);
        nameTextField.setBounds(222, 45, 191, 32);

        mainPanel.add(idTextField);
        idTextField.setBounds(222, 121, 191, 32);
        
        categoryPool();
        mainPanel.add(categoryComboBox);
        categoryComboBox.setBounds(222, 192, 191, 32);

        mainPanel.add(priceTextField);
        priceTextField.setBounds(222, 353, 191, 32);
        mainPanel.add(quantityTextField);
        quantityTextField.setBounds(222, 276, 191, 32);

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

        pack();
    }

    //Add Button
    private void addButtonActionPerformed(ActionEvent evt) {
        clearWarningLabels();
        
        String productId = idTextField.getText();
        String productName = nameTextField.getText();
        String productCategory = (String) categoryComboBox.getSelectedItem();
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
                categoryWarning.setText(" *Please select Product Category .");     
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
                
                PreparedStatement pst=null;
                ResultSet rs=null;
                Statement st=null;
                try {
                String sqlGetPid = "select PRODUCT_ID from PRODUCT_TABLE where PRODUCT_ID = '"+productId+"'  ";
                st = Connect.con.createStatement();
                rs = st.executeQuery(sqlGetPid);

                if(rs.next()){

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
                    pst.setString(3,(String) categoryComboBox.getSelectedItem());
                    pst.setInt(4,Integer.parseInt(productQuantity));
                    pst.setFloat(5,Float.parseFloat(productPrice));
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "New Product Added");
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
    
    private void clearWarningLabels() {
       idWarning.setText("");
       nameWarning.setText("");
       categoryWarning.setText("");
       quantityWarning.setText("");
       priceWarning.setText("");
    }

    private void closeButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed

        
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 
    }
    
    private void categoryPool(){
        categoryComboBox.removeAllItems();
        categoryComboBox.addItem("");
        try{
            Connect.getConnection();
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

    public static void main(String args[]) {
 
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProduct().setVisible(true);
            }
        });
    }
    
   
}
