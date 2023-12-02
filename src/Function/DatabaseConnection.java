package Function;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author MrSimamora
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String HOST = "localhost";
    private static final Integer PORT = 3306;
    private static final String DATABASE = "db_minimarket";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String JDBC_URL = "jdbc:mysql://" + HOST + ":"+ PORT + "/" + DATABASE;
    
    public Integer userid = null;

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }
    
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