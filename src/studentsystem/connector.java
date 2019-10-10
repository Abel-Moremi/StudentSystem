/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meditech_appointment_system_database","root","");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Connection not Established");
        }
        return connection;
    }
}
