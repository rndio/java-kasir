package Function;

import java.sql.*;
import javax.swing.JOptionPane;

public class DBConn {
    private static final String HOST = "localhost";
    private static final Integer PORT = 3306;
    private static final String DATABASE = "db_minimarket";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String JDBC_URL = "jdbc:mysql://" + HOST + ":"+ PORT + "/" + DATABASE;
    
    public static Connection getConnection(){
        try{
            Connection cn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            return cn;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            System.exit(0);
            return null;
        }
    }
}
