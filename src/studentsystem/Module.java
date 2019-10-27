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
public class Module {
    private String mid;
    private String mName;
    private String preRequisite;
    private String mCourse;
    
    public Module(String mid, String mName, String preRequisite, String mCourse){
        this.mid = mid;
        this.mName = mName;
        this.preRequisite = preRequisite;
        this.mCourse = mCourse;
    }
    
    public Module(String mid, String mName){
        this.mid = mid;
        this.mName = mName;
    }
    
    public String getModId(){
        return this.mid;
    }
    
    public String getModName(){
        return this.mName;
    }
    
    public String getPre(){
        return this.preRequisite;
    }
    
    public String getModCourse(){
        return this.mCourse;
    }
    
}
