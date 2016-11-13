
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;


public class MainAddProduct extends JFrame {

    public MainAddProduct() {
        super("Add Product");
        File currentFolder = new File(System.getProperty("user.dir"));  
        ImageIcon icon = new ImageIcon(currentFolder.getPath().concat("/images/icon.jpg"));
        this.setIconImage(icon.getImage());
        initComponents();
    }


    private void initComponents() {

        mainPanel = new JPanel();
        addProductButton = new JButton();
        closeButton = new JButton();
        addCategoryButton = new JButton();
        addStockButton = new JButton();
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(null);

        addCategoryButton.setText("Add Product to New Category");
        addCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCategoryButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addCategoryButton);
        addCategoryButton.setBounds(100, 30, 300, 50);
        
        addProductButton.setText("Add Product to Existing Category");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addProductButton);
        addProductButton.setBounds(100, 100, 300, 50);
        
        addStockButton.setText("Add Stock or Change Product Price");
        addStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockButtonActionPerformed(evt);
            }
        });
        mainPanel.add(addStockButton);
        addStockButton.setBounds(100, 170, 300, 50);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        mainPanel.add(closeButton);
        closeButton.setBounds(100, 240, 300, 50);


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        pack();
    }

    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {

        AddProduct ad = new AddProduct();
        ad.setVisible(true);
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //setSize(screenWidth / 2, screenHeight / 2);
        ad.setLocation(screenWidth/4,screenHeight/4);

    }
    
    private void addCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {

        AddCategory ac = new AddCategory();
        ac.setVisible(true);
        ac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;  
        //setSize(screenWidth / 2, screenHeight / 2);
        ac.setLocation(screenWidth/4,screenHeight/4);
    }
    
    private void addStockButtonActionPerformed(java.awt.event.ActionEvent evt) {

        AddStock as = new AddStock();
        as.setVisible(true);
        as.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        as.setLocation(screenWidth/4,screenHeight/4);
        
    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
       WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
       Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent); 
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainAddProduct().setVisible(true);
            }
        });
    }

    private JButton addProductButton;
    private JButton closeButton;  
    private JButton addStockButton;
    private JButton addCategoryButton;
    private JPanel mainPanel;
}

