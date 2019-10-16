/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package studentsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Zozo
 */
public class StudentSystem {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Connection conn = Connector.ConnectDb();
        ResultSet me = getStudentList(conn);
    }
    
    public static ResultSet getStudentList(Connection conn) throws SQLException{
        
        String listStudents = "SELECT * FROM am_Student";
        Statement stmt = conn.createStatement();
        ResultSet rs =   stmt.executeQuery(listStudents);
       
        while( rs.next()) {
            System.out.println("I was here");
            String id = rs.getString("stu_id");
            String name = rs.getString("stu_name");
            String surname = rs.getString("stu_surname");
            String type = rs.getString("stu_type");
            System.out.println(id + "  " + name + " " + name +" "+ surname +" "+ type );
        }
        
        stmt.close();
        conn.close();

        return rs;
    }
    
}
