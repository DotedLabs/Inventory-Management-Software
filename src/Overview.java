
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

public class Overview extends JFrame {

    public Overview() {
        super("Stock Overview");
        Connect.getConnection();
        
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());
        
        initComponents();
        update();
    }

    void update(){
        PreparedStatement pst=null;
        ResultSet rs=null;
        Statement st=null;
  
        try{
           String sql5 = "Select * from product_table order by product_category";
           pst = Connect.con.prepareStatement(sql5);
           rs = pst.executeQuery();
           productTable.setModel(DbUtils.resultSetToTableModel(rs));
        }    
        catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
        }   
    }

    private void initComponents() {

        mainPanel = new JPanel();
        overviewLabel = new JLabel();
        productTableScrollPane = new JScrollPane();
        productTable = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(51, 204, 255));

        overviewLabel.setFont(new java.awt.Font("Calibri", 1, 30));
        overviewLabel.setText("Stock Overview");

        productTable.setBackground(new java.awt.Color(242, 242, 242));
        //productTable.setBorder(new javax.swing.border.MatteBorder(null));
        productTable.setFont(new java.awt.Font("Calibri", 0, 16));
        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product Id", "Product Name", "Category", "Quantity", "Price"
            }
        ));
        productTable.setIntercellSpacing(new java.awt.Dimension(10, 3));
        
        //To show the table constants in the center
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        productTable.setDefaultRenderer(String.class, centerRenderer);
        //productTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        productTableScrollPane.setViewportView(productTable);

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(productTableScrollPane, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGap(290, 290, 290)
                    .addComponent(overviewLabel, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(overviewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productTableScrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Overview().setVisible(true);
            }
        });
    }

    private JLabel overviewLabel;
    private JPanel mainPanel;
    private JScrollPane productTableScrollPane;
    private JTable productTable;

}
