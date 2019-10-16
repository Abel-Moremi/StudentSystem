/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Zozo
 */
public class connector {
    public static void main(String[] args) {
        ConnectDb();
    }

    public static Connection ConnectDb() {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@10.0.18.2:1521:orcl","mor03625","mor03625");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection not Established");
        }
        return connection;
    }
}
