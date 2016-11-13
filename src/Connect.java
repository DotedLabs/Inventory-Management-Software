
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


abstract public class Connect {
    
    static Connection con=null;
    public static void getConnection(){

        try{
            String driver ="oracle.jdbc.driver.OracleDriver";
            Class.forName(driver);
            String JDBC_DRIVER = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
            String user= "KHANTIL23";
            String pass="khc";
            con = DriverManager.getConnection(JDBC_DRIVER,user,pass);
            System.out.println("\n Connected from Connect.java!");

        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex);        
        }
    }
}
