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
public class Student {
    int id;
    String name;
    String surname;
    String type;
    String password;
    
    public Student(int id, String name, String surname, String type, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.password = password;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getSurname(){
        return this.surname;
    }
    
    public String getType(){
        return this.type;
    }
}
