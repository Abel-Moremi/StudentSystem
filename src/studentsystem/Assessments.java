/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

/**
 *
 * @author rudi
 */
public class Assessments {
   
    int studentId;
    String moduleId;
    String assessment;
    int mark;
    
    public Assessments(int studentId, String moduleId, String assessment, int mark){
        this.studentId = studentId;
        this.moduleId = moduleId;
        this.assessment = assessment;
        this.mark = mark;
    }
    
    public int getStudentID(){
        return this.studentId;
    }
    
    public String getModuleID(){
        return this.moduleId;
    }
    
    public String getAssessment(){
        return this.assessment;
    }
    
    public int getMark(){
        return this.mark;
    }
}
