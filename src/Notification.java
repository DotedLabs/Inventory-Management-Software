
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Notification extends JFrame {
    // Variables declaration
    private JButton closeButton;
    private JLabel notificationLabel;
    private JLabel descriptionLabel;
    private JScrollPane notificationScrollPane;
    private JTable notificationTable;

    public Notification() {
        super("Notification");
        Connect.getConnection();
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());
        initComponents();
        notifyData();
    }
    public void notifyData(){
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st=null;
       
        //Before adding the trigger this code handles notification table
      /*try {
             
                //String user1 = PRODUCT_id.getText();
                pst = Connect.con.prepareStatement("delete from notification_table");
                pst.execute();
                
                String sql = "select PRODUCT_NAME ,  PRODUCT_QUANTITY   from PRODUCT_TABLE  where PRODUCT_QUANTITY < 20 " ;
                System.out.println("Khantil Notification");
                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                int count=0;
                while(rs.next()){
                    count++;
                    if(count==1){
                     //   notice.setText("Following items are about to finish from stock");
                    }
                String sql2 = "insert into notification_table values(?,?)";
                pst= Connect.con.prepareStatement(sql2);
                pst.setString(1,rs.getString("PRODUCT_NAME"));
                pst.setString(2,rs.getString("PRODUCT_QUANTITY"));
                pst.execute();
        
            //JOptionPane.showMessageDialog(null, "data submitted");
                }
   
            if(count==0){
    //       notice.setText("ALL items are available in sufficient quantity");
            }         
              try{
             String sql5 = "Select * from notification_table";
           pst = Connect.con.prepareStatement(sql5);
           rs = pst.executeQuery();
            notificationTable.setModel(DbUtils.resultSetToTableModel(rs));
            }    
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

            }
         catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
        }*/
      
      // After adding trigger
    try{
        String sql5 = "select product_name,product_quantity from notification_table";
        pst = Connect.con.prepareStatement(sql5);
        rs = pst.executeQuery();
        notificationTable.setModel(DbUtils.resultSetToTableModel(rs));
    }    
    catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
   }

    private void initComponents() {

        notificationLabel = new JLabel();
        descriptionLabel = new JLabel();
        
        notificationScrollPane = new JScrollPane();      
        notificationTable = new JTable();
        
        closeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        notificationLabel.setFont(new Font("Verdana", 1, 30));
        notificationLabel.setText("Notification");
        
        notificationTable.setBackground(new Color(255, 255, 255));
        notificationTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        notificationTable.setFont(new Font("Calibri", 0, 16));
        notificationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Product Name", "Product Quantity"
            }
        ));
        notificationTable.setGridColor(new Color(102, 0, 102));
        notificationTable.setIntercellSpacing(new Dimension(5, 5));
        notificationTable.setRowHeight(25);
        notificationScrollPane.setViewportView(notificationTable);

        descriptionLabel.setFont(new Font("Calibri", 1, 20)); // NOI18N
        descriptionLabel.setText("Following are the items which are about to finish from stock");


        closeButton.setText("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap(50,50)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150)
                        .addComponent(notificationLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE))     
                    .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(notificationScrollPane,GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                    .addComponent(closeButton))
                .addContainerGap(50,50)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(notificationLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addComponent(descriptionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(notificationScrollPane, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                     .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                     .addGap(20, 20, 20))
        );
       
        getContentPane().setBackground(new Color(230,230,230));

        pack();
    }

    private void closeButtonActionPerformed(ActionEvent evt) {
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    public static void main(String args[]) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Notification().setVisible(true);
            }
        });
    }
    
    
}
