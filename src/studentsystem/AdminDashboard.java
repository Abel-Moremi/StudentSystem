/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author rudi
 */
public class AdminDashboard extends javax.swing.JFrame {

    Connection con;
    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() throws SQLException  {
        initComponents();
        createConnection();
        courses();
        students();
        setLocationRelativeTo(null);
        
    }
    
       void createConnection() throws SQLException{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_admin", "root","");
                System.out.println("connection successful");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
       /**
        *Courses method selects all the courses from the DB and appends
        *the "select courses" drop down 
        */
        public void courses(){
            try {
                String query = "SELECT * FROM am_course";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);


                while(rs.next()){
                    courses.addItem(rs.getString("crs_id"));
                }

              } catch (SQLException ex) {
                  Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
            
        public void students(){
            try {
                String query = "SELECT * FROM am_student";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
          
                
                while(rs.next()){
                    students.addItem(Integer.toString(rs.getInt("stu_id")));
                }
            
             } catch (SQLException ex) {
                Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void enroll(){

            long time = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(time);
            
            Object yr = enrollYear.getSelectedItem();
            String year = yr.toString();
            int enrollmentYear = 0;
            
            if(year.equals("year 1")){
                enrollmentYear = 1;
            }else if(year.equals("year 2")){
                enrollmentYear = 2;
            }else if(year.equals("year 3")){
                enrollmentYear = 3;
            }else if(year.equals("year 4")){
                enrollmentYear = 4;
            }else if(year.equals("year 5")){
                enrollmentYear = 5;
            }
            
            Object crs = courses.getSelectedItem();
            String crsID = crs.toString();
            
            Object std = students.getSelectedItem();
            int stdID = Integer.parseInt(std.toString());
        
                String query = "INSERT INTO am_courseregistration"
                    + "(crg_year, crg_crs_id, crg_stu_id, crg_date_registered) VALUES"
                    + "(?,?,?,?)";
          try {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setInt(1, enrollmentYear);
            stmt.setString(2, crsID);
            stmt.setInt(3, stdID);
            stmt.setDate(4, date);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student Enrolled!!", "Enroll Student: "+ "Successful", JOptionPane.INFORMATION_MESSAGE);
    
            stmt.close();
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "You can not enroll a student to more than one course", "Enroll Student: "+ "Failed", JOptionPane.INFORMATION_MESSAGE);
        }
          
          students.setSelectedIndex(0);
          courses.setSelectedIndex(0);
          enrollYear.setSelectedIndex(0);
          
          
        }
        
    public void listModules(String course){
        ArrayList<Module> list = moduleList(course);
        DefaultTableModel model = (DefaultTableModel) moduleTable.getModel();
        Object[] row = new Object[2];
            
        for(int i = 0; i<list.size(); i++){
            row[0] = list.get(i).getModId();
            row[1] = list.get(i).getModName();
            model.addRow(row);
        }
            
    }
            
    public ArrayList<Module> moduleList(String course){
        
        ArrayList<Module> modules = new ArrayList<Module>();
           
        try {
            String query = "SELECT mod_id, mod_name FROM am_module WHERE mod_course='"+course+"'";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Module tr;
                
            while(rs.next()){
                tr = new Module(rs.getString("mod_id"), rs.getString("mod_name"));
                modules.add(tr);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modules;
    }

    public String findCourse(){
        String course = "";
        String courseName = "";
        try {
            String query = "SELECT am_course.crs_id , am_course.crs_name FROM am_course\n" +
                            "INNER JOIN am_courseregistration ON am_course.crs_id = am_courseregistration.crg_crs_id\n" +
                            "WHERE am_courseregistration.crg_stu_id ="+Integer.parseInt(searchField.getText());
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Module tr;
                
            while(rs.next()){

                course = rs.getString("crs_id");
                courseName = rs.getString("crs_name");
            }
            
            if(course.equals("")){
                  JOptionPane.showMessageDialog(null, "This student does not exist", "Search Student: "+ "Failed", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        targetCourse.setText(courseName);
        return course;
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
        jPanel2 = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        viewAllButton = new javax.swing.JButton();
        enrolledStudentsButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        assessmentButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        studName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        studSurname = new javax.swing.JTextField();
        studType = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        studPassword = new javax.swing.JPasswordField();
        addStudentButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        enrollYear = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        courses = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        students = new javax.swing.JComboBox<>();
        enrollButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        moduleTable = new javax.swing.JTable();
        targetCourse = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        logoutButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 0, 0));
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        viewAllButton.setBackground(new java.awt.Color(255, 255, 255));
        viewAllButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        viewAllButton.setForeground(new java.awt.Color(0, 204, 204));
        viewAllButton.setText("Enrolled Students");
        viewAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewAllButtonActionPerformed(evt);
            }
        });

        enrolledStudentsButton.setBackground(new java.awt.Color(255, 255, 255));
        enrolledStudentsButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        enrolledStudentsButton.setForeground(new java.awt.Color(0, 204, 204));
        enrolledStudentsButton.setText("All Students");
        enrolledStudentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrolledStudentsButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 36)); // NOI18N
        jLabel10.setText("Admin");

        assessmentButton.setBackground(new java.awt.Color(255, 255, 255));
        assessmentButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        assessmentButton.setForeground(new java.awt.Color(0, 204, 204));
        assessmentButton.setText("Assess Student");
        assessmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assessmentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(enrolledStudentsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(assessmentButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel10)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(enrolledStudentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(viewAllButton, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(assessmentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel3.setText("Add New Student");

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Student Name");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("Student Surname");

        studType.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        studType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select option", "postgraduate", "undergraduate", " " }));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Degree Level");

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("System Access Password");

        addStudentButton.setBackground(new java.awt.Color(255, 255, 255));
        addStudentButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        addStudentButton.setForeground(new java.awt.Color(0, 204, 204));
        addStudentButton.setText("Add Student");
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addComponent(jLabel3)
                .addContainerGap(172, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(studName)
                        .addComponent(studSurname)
                        .addComponent(studType, 0, 338, Short.MAX_VALUE)
                        .addComponent(studPassword))
                    .addComponent(addStudentButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel6.setText("Enroll Student");

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Entry Level");

        enrollYear.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        enrollYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "year 1", "year 2", "year 3", "year 4", "year 5" }));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("Course");

        courses.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        courses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Course" }));
        courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("Student");

        students.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        students.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Student by ID" }));
        students.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentsActionPerformed(evt);
            }
        });

        enrollButton.setBackground(new java.awt.Color(255, 255, 255));
        enrollButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        enrollButton.setForeground(new java.awt.Color(0, 204, 204));
        enrollButton.setText("Enroll");
        enrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(enrollYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(courses, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(students, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel6)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enrollYear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(courses, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(students, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        searchButton.setBackground(new java.awt.Color(255, 255, 255));
        searchButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        searchButton.setForeground(new java.awt.Color(0, 204, 204));
        searchButton.setText("search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        moduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Module ID", "Module Name"
            }
        ));
        jScrollPane1.setViewportView(moduleTable);

        targetCourse.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        targetCourse.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 24)); // NOI18N
        jLabel11.setText("Search Student By ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 73, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(targetCourse)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel11))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addGap(46, 46, 46)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(targetCourse)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 90, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStudentButtonActionPerformed

        Object type = studType.getSelectedItem();
        String studentType = type.toString();
       
        
                String query = "INSERT INTO am_student"
                    + "(stu_name, stu_surname, stu_type, stu_password) VALUES"
                    + "(?,?,?,?)";
          try {
            PreparedStatement stmt = con.prepareStatement(query);
            
            stmt.setString(1, studName.getText());
            stmt.setString(2, studSurname.getText());
            stmt.setString(3, studentType);
            stmt.setString(4, studPassword.getText());

            
            stmt.executeUpdate();
       
            System.out.println("record inserted^");
            stmt.close();
            //con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          studName.setText("");
          studSurname.setText("");
          studPassword.setText("");
          studType.setSelectedIndex(0);
          JOptionPane.showMessageDialog(null, "Record Added", "Update Student: "+ "Success", JOptionPane.INFORMATION_MESSAGE);        // TODO add your handling code here:
    }//GEN-LAST:event_addStudentButtonActionPerformed

    private void coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coursesActionPerformed

    private void enrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButtonActionPerformed
        enroll();
    }//GEN-LAST:event_enrollButtonActionPerformed

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        try {
            new AdminLogin().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
           this.setVisible(false);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void viewAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewAllButtonActionPerformed
        try {
            new AllStudents().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
           this.setVisible(false);
    }//GEN-LAST:event_viewAllButtonActionPerformed

    private void enrolledStudentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrolledStudentsButtonActionPerformed
    try {
            new StudentList().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
           this.setVisible(false);
    }//GEN-LAST:event_enrolledStudentsButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) moduleTable.getModel();
        model.setRowCount(0);
        String course = findCourse();
        listModules(course);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void studentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentsActionPerformed

    private void assessmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assessmentButtonActionPerformed
           try {
            new Assessment().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
           this.setVisible(false);
    }//GEN-LAST:event_assessmentButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdminDashboard().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addStudentButton;
    private javax.swing.JButton assessmentButton;
    private javax.swing.JComboBox<String> courses;
    private javax.swing.JButton enrollButton;
    private javax.swing.JComboBox<String> enrollYear;
    private javax.swing.JButton enrolledStudentsButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTable moduleTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField studName;
    private javax.swing.JPasswordField studPassword;
    private javax.swing.JTextField studSurname;
    private javax.swing.JComboBox<String> studType;
    private javax.swing.JComboBox<String> students;
    private javax.swing.JLabel targetCourse;
    private javax.swing.JButton viewAllButton;
    // End of variables declaration//GEN-END:variables
}
