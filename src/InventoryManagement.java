
//import static WelcomeScreen.myImage;
//import com.sun.jndi.ldap.Connection;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class InventoryManagement extends JFrame {
        private JButton addProductButton;
    private JButton stockButton;
    private JButton notificationButton;
    private JButton invoiceButton;
    private JButton logoutButton;

    private JMenu productMenu;
    private JMenu viewMenu;
    private JMenu detailsMenu;

    private JMenuBar mainMenuBar;
    private JMenuItem dashboardMenuItem;
    private JMenuItem stockMenuItem;
    private JMenuItem chartsMenuItem;
    private JMenuItem graphsMenuItem;
    private JMenuItem notificationMenuItem;
    private JMenuItem dataAnalysisMenuItem;

    private JMenuItem addProductMenuItem;
    private JMenuItem searchProductMenuItem;
    private JMenuItem invoiceMenuItem;

    private JPanel sidePanel;
    
    private JLabel logoLabel;


     File currentFolder;
     int sessionId;
     
    public InventoryManagement(int sid) {
        super("Inventory Manegement");
        super.setBackground(Color.WHITE);
        //Session id
        sessionId=sid;
        //Establish Connection
        Connect.getConnection();           
        //System.out.println("\n In the constructor of inventory form");
        currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());
        initComponents();
    }

    private void initComponents() {
        
        logoLabel = new JLabel();

        sidePanel = new JPanel();
        
        addProductButton = new JButton();
        stockButton = new JButton();
        notificationButton = new JButton();
        invoiceButton = new JButton();
        logoutButton = new JButton();
        
        mainMenuBar = new JMenuBar();
        //Product Menu
        productMenu = new JMenu();
        addProductMenuItem = new JMenuItem();
        searchProductMenuItem = new JMenuItem();
        invoiceMenuItem = new JMenuItem();
        stockMenuItem = new JMenuItem();
        //Charts Menu
        viewMenu = new JMenu();
        dashboardMenuItem = new JMenuItem();
        chartsMenuItem = new JMenuItem();
        graphsMenuItem = new JMenuItem();
        //Details Menu
        detailsMenu = new JMenu();
        notificationMenuItem = new JMenuItem();
        dataAnalysisMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        sidePanel.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        try{
            String logoPath = currentFolder.getPath().concat("/images/logoMainBig.jpg");
            logoLabel.setIcon(new ImageIcon(ImageIO.read(new File(logoPath))));
            logoLabel.setBounds(450, 10, 700, 600);
            this.add(logoLabel);
        }catch(IOException e){
            e.printStackTrace();
        }
        
        addProductButton.setText("Add Product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        stockButton.setText("Stock");
        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                stockButtonActionPerformed(evt);
            }
        });

        notificationButton.setText("Notification");
        notificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                notificationButtonActionPerformed(evt);
            }
        });

        invoiceButton.setText("Invoice");
        invoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                invoiceButtonActionPerformed(evt);
            }
        });

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(addProductButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(invoiceButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(notificationButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stockButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(250,250,250)
                .addComponent(addProductButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(invoiceButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stockButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(notificationButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18,18,18)
                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(0, Short.MAX_VALUE, Short.MAX_VALUE))
        );
        productMenu.setText("Products");
        productMenu.setMargin(new java.awt.Insets(0, 10, 0, 10));
        productMenu.setMaximumSize(new java.awt.Dimension(80, 32767));

        addProductMenuItem.setText("Add Product");
        addProductMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });
        productMenu.add(addProductMenuItem);

        searchProductMenuItem.setText("Search Product");
        searchProductMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchProductMenuItemActionPerformed(evt);
            }
        });
        productMenu.add(searchProductMenuItem);

        invoiceMenuItem.setText("Invoice");
        invoiceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                invoiceButtonActionPerformed(evt);
            }
        });
        productMenu.add(invoiceMenuItem);

        stockMenuItem.setText("Product Overview");
        stockMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                stockButtonActionPerformed(evt);
            }
        });
        productMenu.add(stockMenuItem);

        mainMenuBar.add(productMenu);

        viewMenu.setText("View");
        viewMenu.setMargin(new java.awt.Insets(0, 10, 0, 10));
        viewMenu.setMaximumSize(new java.awt.Dimension(60, 32767));

        dashboardMenuItem.setText("Dashboard");
        dashboardMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dashboardMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(dashboardMenuItem);

        chartsMenuItem.setText("Charts");
        chartsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                chartsMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(chartsMenuItem);

        graphsMenuItem.setText("Graphs");
        graphsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                graphsMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(graphsMenuItem);

        mainMenuBar.add(viewMenu);

        detailsMenu.setText("Details");
        detailsMenu.setMargin(new Insets(0, 10, 0, 10));
        detailsMenu.setMaximumSize(new Dimension(60, 32767));

        notificationMenuItem.setText("Notification");
        notificationMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                notificationButtonActionPerformed(evt);
            }
        });
        detailsMenu.add(notificationMenuItem);

        dataAnalysisMenuItem.setText("Data Anlysis");
        dataAnalysisMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dataAnalysisMenuItemActionPerformed(evt);
            }
        });
        detailsMenu.add(dataAnalysisMenuItem);

        mainMenuBar.add(detailsMenu);

        setJMenuBar(mainMenuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sidePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sidePanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
        );
        getContentPane().setBackground(Color.WHITE);
        pack();
    }
    
        private void addProductButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        MainAddProduct mad = new MainAddProduct();
        mad.setVisible(true);
        mad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
    
        //setSize(screenWidth / 2, screenHeight / 2);
        mad.setLocation(screenWidth/4,screenHeight/4); 
    }
    
    //Inovice Button
    private void invoiceButtonActionPerformed(ActionEvent evt) {

        PurchaseProduct p = new PurchaseProduct();
        p.setVisible(true);
                p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Toolkit tk = Toolkit.getDefaultToolkit();
                Dimension screenSize = tk.getScreenSize();
                int screenHeight = screenSize.height;
                int screenWidth = screenSize.width;
        p.setLocation(screenWidth/12,0);

    }


    //Stock Button
    private void stockButtonActionPerformed(ActionEvent evt) {

        Overview o = new Overview();
        o.update();
        o.setVisible(true);          
        o.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
    
        //setSize(screenWidth / 2, screenHeight / 2);
        o.setLocation(screenWidth/4,screenHeight/12);
    }
    
    //Notification Button Action Performed
    private void notificationButtonActionPerformed(ActionEvent evt) {

        Notification n = new Notification();
        //n.notifyy();
        n.setVisible(true);
        n.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
    
        //setSize(screenWidth / 2, screenHeight / 2);
        n.setLocation(screenWidth/4,screenHeight/12);
  
    }
    
    //Logout button
    private void logoutButtonActionPerformed(ActionEvent evt) {
        try{
            PreparedStatement pst=null;                                    
            String sql = "update emp_work set logout = sysdate where sessionid = ?";
            pst = Connect.con.prepareStatement(sql);
            pst.setInt(1, sessionId);
            pst.execute();                                   
         }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
         }
        
        JOptionPane.showMessageDialog(null,"LOGGED OUT");
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 
    }

    private void chartsMenuItemActionPerformed(ActionEvent evt) {
        Statement st=null;
        ResultSet rs=null;

        //DefaultCategoryDataset objDataset = new DefaultCategoryDataset();
        DefaultPieDataset objDataset = new DefaultPieDataset();
           try {
             
                //String user1 = PRODUCT_id.getText();
                String sql = "select PRODUCT_NAME ,PRODUCT_QUANTITY from PRODUCT_TABLE  " ;
                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    int n = Integer.parseInt(rs.getString("PRODUCT_QUANTITY"));                   
                    objDataset.setValue(rs.getString("PRODUCT_NAME"),n);
                     }
                
            } 
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
        }
        //DefaultPieDataset objDataset = new DefaultPieDataset();
       
        
       JFreeChart objChart = ChartFactory.createPieChart (
    "Pie Chart",   //Chart title
    objDataset,          //Chart Data 
    true,               // include legend?
    true,               // include tooltips?
    false               // include URLs?
    );
       ChartFrame frame = new ChartFrame("Pie Chart", objChart);
frame.pack();
frame.setVisible(true);
  Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
   
 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setLocation(screenWidth/4,screenHeight/4);

    }

    private void searchProductMenuItemActionPerformed(ActionEvent evt) {
        

        SearchProduct sd = new  SearchProduct();
        sd.setVisible(true);
        sd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        sd.setLocation(screenWidth/4,screenHeight/4);
 
    }


    private void dashboardMenuItemActionPerformed(ActionEvent evt) {
        Statement st=null;
        ResultSet rs=null;

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();
        
           try {

                String sql = "select PRODUCT_NAME ,PRODUCT_QUANTITY from PRODUCT_TABLE  " ;

                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    int n = Integer.parseInt(rs.getString("PRODUCT_QUANTITY"));
                    
                    objDataset.setValue(n,"Q1",rs.getString("PRODUCT_NAME"));

                     }
                
            } 
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
        }
        
        
JFreeChart objChart = ChartFactory.createBarChart(
       "Bar Chart",     //Chart title
    "Different Products",     //Domain axis label
    "Product quantity",         //Range axis label
    objDataset,         //Chart Data 
    PlotOrientation.VERTICAL, // orientation
    true,             // include legend?
    true,             // include tooltips?
    false             // include URLs?
);
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    int screenHeight = screenSize.height;
    int screenWidth = screenSize.width;
   
ChartFrame frame = new ChartFrame("Bar Chart", objChart);
frame.pack();
frame.setVisible(true);
frame.setLocation(screenWidth/4,screenHeight/4);
 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);                

    }



    private void graphsMenuItemActionPerformed(ActionEvent evt) {

        Statement st=null;
        ResultSet rs=null;

        DefaultCategoryDataset objDataset = new DefaultCategoryDataset();
        
           try {
             
                String sql = "select PRODUCT_NAME ,PRODUCT_QUANTITY from PRODUCT_TABLE  " ;

                st= Connect.con.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    int n = Integer.parseInt(rs.getString("PRODUCT_QUANTITY"));
                    
                    objDataset.setValue(new Double(n), "Quantity", rs.getString("PRODUCT_NAME"));
                    //jTextarea1.setText(male.getText());
                     }
                JFreeChart chart = ChartFactory.createLineChart3D("Line Chart", "products", "Quantity", objDataset, PlotOrientation.VERTICAL, false, false, false);
            chart.setBackgroundPaint(Color.WHITE);
            chart.getTitle().setPaint(Color.RED);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.BLUE);
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
 
            ChartFrame frame = new ChartFrame("Line Chart", chart);
            frame.setVisible(true);
            frame.setSize(400, 500);
            frame.setLocation(screenWidth/4,screenHeight/4);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
            } 
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
        }

    }


    private void dataAnalysisMenuItemActionPerformed(ActionEvent evt) {

        DataAnalysis d = new DataAnalysis();
        
        d.setVisible(true);
        d.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //setSize(screenWidth / 2, screenHeight / 2);
        d.setLocation(screenWidth/4,screenHeight/4);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InventoryManagement n = new InventoryManagement(0);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                n.setSize(screenSize.width, screenSize.height);
                n.setVisible(true);
                
            }
        });
    }

}
