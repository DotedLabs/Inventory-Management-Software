
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DataAnalysis extends javax.swing.JFrame {

    public ArrayList<String> Data;
    String tmp;
    Set<Integer> set;
    int numtr = 0,numpro = 0;

    public DataAnalysis() {
            initComponents();
            Connect.getConnection();
           Data =  new ArrayList<String>();
            set = new HashSet<Integer>();
            tmp = "";
        try{
             analysis();
        } catch(Exception e){
            e.printStackTrace();
        }
       
    }
    void log(String message) {
        System.out.println(message);
    }
    //public ArrayList<String> Data;
public void  analysis() throws SQLException{
   
        int oldoid=0 ,curoid=0;
        //Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        /*try {
            String driver ="oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            String JDBC_DRIVER = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
            String user= "KHANTIL23";
            String pass="khc";
            con = DriverManager.getConnection(JDBC_DRIVER,user,pass);
        //log("insidedb");
        
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);
        } */      
        
        try{
         //String user1 = PRODUCT_id.getText();
         String sql = "select Product_id , Order_no from data_table order by Order_no" ;
         
         st= Connect.con.createStatement();
         rs = st.executeQuery(sql);
                //BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
                //Data =  new ArrayList<String>();
                // rs.next();
                int flag = 0;
                int count =0;
                
                
               // log("befwhile");
                while(rs.next()){
                    
                    int p_id  = Integer.parseInt(rs.getString("Product_id"));
                    System.out.println("Product ID: "+p_id);
                    set.add(p_id);
                   //log(p_id+"");
                     curoid  = Integer.parseInt(rs.getString("Order_no"));
                     System.out.print("    Order No :  "+curoid);
                     if(flag == 0){flag = 1;oldoid = curoid;}   //first order is not to be added??? why??
                     if(oldoid != curoid){
                        Data.add(tmp);
                       
                        //log("t" + tmp);
                        tmp = p_id + " ";
                        
                        oldoid = curoid;
                        
                    } else {
                        
                        tmp += p_id + " ";
                    }
                     //log("t " + tmp);
                    //log(curoid+"");
                     System.out.println("  tmp :  "+ tmp);
                }
                Data.add(tmp);
                //log("aftwhile");
                
                numtr = curoid; //last order id for number of transactions i,e, order ids
                //log(numtr+"");
                //log(curoid+"");
                numpro = set.size();    //total number of orders from hashset size// no of items 
                // different products purchased
                //log(numpro+"");
//                for( int i = 0; i < numtr; i++) {
//                    System.out.println(Data.get(i));
////                    
//                }
                System.out.println("\n\n Data - size is "+Data.size()+" and numbtr i.e.number of transactions is "+numtr+" numpro is "+numpro);
                    Apriori a = new  Apriori(Data,numtr,numpro,0.5);
                    System.out.println("\n after calling apriori from analysis()");
                    List<int[]> l = a.go();
                    //khc
                    if(l.isEmpty()){
                        System.out.println("\n List returned from Apriori a.go() is empty.");
                    }else{
                        System.out.println("\n List returned from Apriori a.go() has size : "+l.size());
                    }
                    
                    System.out.println("\n Inside exceptpion 1 line data_analysis");
                     String temp = "\n";
                    for(int[] i : l) {
                        temp = temp + "[ ";
                        for(int j:i) {
                            //System.out.print(j + " ");
                            temp = temp + j + " ";
                        }
                        temp = temp + "]\n";
                        temp = temp + "-----------------------------------------\n";
                    }
                    
                    String kiss = " ";
                    System.out.println(" 133");
                    
                    
                    
                    int i=0;
                    /*while(i<l.size()){
                        int [] j = l.get(i);
                        System.out.println(" 138 138");
                    i++;
                    }*/
                    
                    System.out.println(" Temp : at line 131     "+kiss);
                    message.setText("Following are different Items that are associated with each other" + temp );
                   
                    
                    //log(temp);
                    
                  //nPane.showMessageDialog(null, "data submitted");
        }    catch(Exception ex){
            System.out.println("\n Inside exceptpion 136 line data_analysis");
            JOptionPane.showMessageDialog(null,ex);
        }    
        try {
            rs.close();
        } catch (SQLException ex) {
            //System.out.println("\n Inside exceptpion 143 line data_analysis");
            Logger.getLogger(DataAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        st.close();
    
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setForeground(new java.awt.Color(102, 255, 102));

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 30)); // NOI18N
        jLabel1.setText("Data Analysis");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataAnalysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               //DataAnalysis obj = new DataAnalysis();
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea message;
    // End of variables declaration//GEN-END:variables
}
