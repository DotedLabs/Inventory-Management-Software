

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

import net.proteanit.sql.DbUtils;

public class PurchaseProduct extends JFrame {
    
    // Variables declaration
    private JTextField customerNumber;
    private JTextField productQuantity;
    private JTextField customerName;
    private JTextField orderNumber;
    private JTextField totalBill;
    
    private JButton printBillButton;
    private JButton submitButton;
    
    private JLabel invoiceLabel;
    private JLabel quantityLabel;
    private JLabel customerNumberLabel;
    private JLabel productLabel;
    private JLabel orderNumberLabel;
    private JLabel categoryLabel;
    private JLabel customerNameLabel;
    private JLabel totalLabel;
    private JLabel categoryWarning;
    private JLabel productWarning;
    private JLabel quantityWarning;
    
    
    private JPanel billPanel;
    private JPanel titlePanel;
    private JPanel customerPanel;
    private JPanel productPanel;
    
    private JScrollPane billDetailsScrollPane;
    
    
    private JTable billTable;
    
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> productComboBox;

    public PurchaseProduct() {
        super("Invoice");
        setBackground(Color.WHITE);
        Connect.getConnection();
        
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());
        
        initComponents();
    }

    private void initComponents() {

        titlePanel = new JPanel();
        invoiceLabel = new JLabel();
        
        //Customer Panel
        customerPanel = new JPanel();
        customerNumberLabel = new JLabel();
        orderNumberLabel = new JLabel();
        customerNameLabel = new JLabel();
        customerNumber = new JTextField();
        customerName = new JTextField();
        orderNumber = new JTextField();
        orderNumber.setText(""+setOrderNo());
        orderNumber.setEditable(false);
        
        //ProductPanel
        productPanel = new JPanel();
        categoryLabel = new JLabel();
        productLabel = new JLabel();
        quantityLabel = new JLabel();
        submitButton = new JButton();
        categoryComboBox = new JComboBox<String>();
        categoryPool();
        productComboBox = new JComboBox<String> ();
        productQuantity = new JTextField();
        billDetailsScrollPane = new JScrollPane();
        billTable = new JTable();
        categoryWarning = new JLabel("");
        productWarning = new JLabel("");
        quantityWarning = new JLabel("");
        categoryWarning.setForeground(Color.RED);
        productWarning.setForeground(Color.RED);
        quantityWarning.setForeground(Color.RED);
    
        //Final bill amount panel
        billPanel = new JPanel();
        printBillButton = new JButton();
        totalLabel = new JLabel();
        totalBill = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        titlePanel.setBackground(new Color(230, 230, 230));

        invoiceLabel.setFont(new Font("Verdana", 1, 28)); // NOI18N
        invoiceLabel.setText("Invoice");

        GroupLayout titlePanelLayout = new GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGap(536, 536, 536)
                .addComponent(invoiceLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(invoiceLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        customerPanel.setBackground(new Color(255, 255, 255));

        customerNumberLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        customerNumberLabel.setText("Customer Number");

        orderNumberLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        orderNumberLabel.setText("Order Number");

        customerNameLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        customerNameLabel.setText("Customer Name");

        GroupLayout customerPanelLayout = new GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(customerNumberLabel)
                .addGap(35, 35, 35)
                .addComponent(customerNumber, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(customerNameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, 58)
                .addComponent(customerName, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(orderNumberLabel)
                .addGap(41, 41, 41)
                .addComponent(orderNumber, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(customerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameLabel)
                    .addComponent(orderNumberLabel)
                    .addComponent(customerNumberLabel)
                    .addComponent(customerNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        productPanel.setBackground(new Color(255, 255,  255));

        categoryLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        categoryLabel.setText("Category");

        productLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        productLabel.setText("Product Name");

        quantityLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        quantityLabel.setText("Quantity");

        submitButton.setText("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        
        //To populate products from selected categoryComboBox
        
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
        
        //to remove warning  product label
        
        productComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie){
                productWarning.setText("");
            }
        });

        GroupLayout productPanelLayout = new GroupLayout(productPanel);
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(categoryLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(categoryComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, 174)
                .addComponent(productLabel)
                .addGap(33, 33, 33)
                .addComponent(productComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(quantityLabel)
                .addGap(40, 40, 40)
                .addComponent(productQuantity, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addGap(104, 104, 104))
            .addGroup(productPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(categoryWarning, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(productWarning, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108)
                .addComponent(quantityWarning, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, productPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(productPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLabel)
                    .addComponent(productLabel)
                    .addComponent(quantityLabel)
                    .addComponent(submitButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(productComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(productQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(productPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryWarning)
                    .addComponent(productWarning)
                    .addComponent(quantityWarning))
                .addGap(20,20,20))
        );

        billTable.setBackground(new Color(255, 255, 255));
        billTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        billTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                
            },
            new String [] {
                "PRODUCT ID", "PRODUCT NAME", "UNIT PRICE", "QUANTITY", "TOTAL PRICE"
            }
        ));
        billTable.setIntercellSpacing(new Dimension(4, 4));
        billTable.setRowHeight(20);
        billDetailsScrollPane.setViewportView(billTable);

        billPanel.setBackground(new Color(255, 255, 255));
        billPanel.setLayout(null);

        printBillButton.setText("Print Bill");
        printBillButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                printBillButtonActionPerformed(evt);
            }
        });
        billPanel.add(printBillButton);
        printBillButton.setBounds(670, 25, 120, 35);

        totalLabel.setFont(new Font("Calibri", 1, 18)); // NOI18N
        totalLabel.setText("Total");
        billPanel.add(totalLabel);
        totalLabel.setBounds(239, 30, 39, 22);
        
        totalBill.setText(" 0.00");
        totalBill.setEditable(false);
        billPanel.add(totalBill);
        totalBill.setBounds(350, 20, 143, 40);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(customerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(billDetailsScrollPane, GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(billDetailsScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                //.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(productPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(billPanel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    // To insert item to the bill list
    private void submitButtonActionPerformed(ActionEvent evt) {
        
        clearWarningLabels();
        
        String cat = (String) categoryComboBox.getSelectedItem();
        String product_name = (String) productComboBox.getSelectedItem();
        String product_quantity = productQuantity.getText();
        boolean warning = false;
        try{
            if(cat.isEmpty()){
                categoryWarning.setText("Category must be selected!");
                warning = true;
            }
            if(product_name.isEmpty()){
                productWarning.setText("Product must be selected!");
                warning = true;
            }
            if(product_quantity.isEmpty()){
                quantityWarning.setText("Product quantity must not be empty!");
                warning = true;
            }
            if(!product_quantity.isEmpty() && Integer.parseInt(product_quantity)<=0){
                quantityWarning.setText("Product quantity must be positive integer value!");
                warning = true;
            }
            if(warning){
                throw new ProductException(" Bill data not valid!");
            }else{
                PreparedStatement pst=null;
                ResultSet rs=null;
                Statement st=null;
                String pid = null;
                 try{
                    String getPid = "select product_id from product_table where product_name = '"+product_name+"' ";
                    st= Connect.con.createStatement();
                    rs = st.executeQuery(getPid);
                    if(rs.next()){
                        pid = (String) rs.getString("product_id");
                    }else{
                        System.out.println("\n Product Id not fetched!");
                    }

                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null,ex);
                }
                try {
                  String sql = "insert into data_table values(?,?,?,?)";
                  pst= Connect.con.prepareStatement(sql);
                  pst.setString(1,customerNumber.getText());
                  pst.setString(2,pid);
                  pst.setLong(3,Long.parseLong(orderNumber.getText()));
                  pst.setString(4,product_quantity);
                  pst.execute();

                   //JOptionPane.showMessageDialog(null, "data submitted");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }


            try {
                String sql;
                sql = "update PRODUCT_TABLE set PRODUCT_QUANTITY = PRODUCT_QUANTITY - '"+product_quantity+"' where PRODUCT_ID = '"+pid+"'";
                pst= Connect.con.prepareStatement(sql);

                pst.execute();
                JOptionPane.showMessageDialog(null, "data updated successfully");

                        sql = "select  PRODUCT_QUANTITY   from PRODUCT_TABLE  where PRODUCT_QUANTITY < 20 AND PRODUCT_ID = '"+pid+"'  " ;         
                        st= Connect.con.createStatement();
                        rs = st.executeQuery(sql);
                        //int count=0;
                        if(rs.next()){
                          Notification n = new Notification();
                          n.setVisible(true);
                          n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                          Toolkit tk = Toolkit.getDefaultToolkit();
                          Dimension screenSize = tk.getScreenSize();
                          int screenHeight = screenSize.height;
                          int screenWidth = screenSize.width;
                          //setSize(screenWidth / 2, screenHeight / 2);
                          n.setLocation(screenWidth/16,screenHeight/8);      
                        }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }

            try {
                String sql = "select PRICE  from PRODUCT_TABLE  where PRODUCT_ID ='"+pid+"'" ;
                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                float price = 0;
                if(rs.next()){
                    price  = rs.getFloat("PRICE");
                }else{
                    System.out.println("\n Product price not found!");
                }
                 float total = Integer.parseInt(product_quantity) * price ;

                String sql2 = "insert into display_table values(?,?,?,?,?)";
                pst= Connect.con.prepareStatement(sql2);
                pst.setString(1,pid);
                pst.setString(2,product_name);
                pst.setString(3,String.valueOf(price));
                pst.setString(4,product_quantity);
                pst.setString(5,String.valueOf(total));
                pst.execute();

                 //  JOptionPane.showMessageDialog(null, "data submitted");
            }    catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
            try{
                String sql5 = "Select * from display_table";
                pst = Connect.con.prepareStatement(sql5);
                rs = pst.executeQuery();
                //To set table from select query for bill details
                billTable.setModel(DbUtils.resultSetToTableModel(rs));
            }    
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            //Updating Total Bill Amount after each product is added
            try {
                String sql = "SELECT SUM(PRODUCT_TOTAL) FROM display_table " ;
                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                String sum;
                if(rs.next()){
                    sum = rs.getString("SUM(PRODUCT_TOTAL)");
                }else{
                    sum = "0.0";
                }
                totalBill.setText(String.valueOf(sum));
                        /*float sum =0;
                        if(rs.next()){
                            String s = rs.getString("SUM(PRODUCT_TOTAL)");
                            sum = Float.parseFloat(s);
                        }*/

                    }catch(Exception ex){
                       JOptionPane.showMessageDialog(null,ex);
                }
            clearProduct();
            }
        }catch (ProductException pe){
            pe.printStackTrace();
        }
        
        
           
    }

    private void printBillButtonActionPerformed(ActionEvent evt) {
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st=null;
        try {
            String sql = "delete FROM display_table"; 
            pst= Connect.con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Bill is printed");        
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);   
    }
    
    
    private void clearProduct(){
        categoryComboBox.removeAllItems();
        productComboBox.removeAllItems();
        productQuantity.setText("");
        categoryPool();
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
         clearWarningLabels();
         
        String cat = (String) categoryComboBox.getSelectedItem();
        ResultSet rs = null;
        try{
            productComboBox.removeAllItems();            
            java.sql.Statement smt = Connect.con.createStatement();
            
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
    
    private long setOrderNo(){
        try{
            
            Statement smt = Connect.con.createStatement();
            ResultSet rs = smt.executeQuery("select max(Order_no) from data_table");
            //ResultSet rs = smt.executeQuery("select Order_no from data_table where rownum = 1 order by order_no desc");
            
            if(rs.next()){
                long o = rs.getLong("max(Order_no)");
                System.out.println("Max  Ordr No: "+o);
                return o+1;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return 1;
    }
    
    public void clearWarningLabels(){
        categoryWarning.setText("");
        productWarning.setText("");
        quantityWarning.setText("");     
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseProduct().setVisible(true);
    
            }
        });
    }

}
