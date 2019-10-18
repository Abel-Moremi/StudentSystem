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
public class ModuleRegistration {
    String semester;
    String module;
    int student;
    
    public ModuleRegistration(String semester, int student ,String module){
        this.semester = semester;
        this.module = module;
        this.student = student;
    }
    
    public String getSemester(){
        return this.semester;
    }
    
    public String getModule(){
        return this.module;
    }
    
    public int getStudent(){
        return this.student;
    }
}
