/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.ArrayList;
import Users.*;
import Courses.*;

/**
 *
 * @author simonnewham
 */
public class DataBase {
    
    public static ArrayList<User> users = new ArrayList<User>();
   
    public static ArrayList<Course> courses = new ArrayList<Course>();
    
    public DataBase(){
       
    }
     
    public Student findStudent(String studentName){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRole() == "S"){
                if (users.get(i).getUser().equals(studentName)== true){
                    return (Student) users.get(i);
                }
            }
        }
        return null;
    }
    
    public static ArrayList<User>getUsers(){
        
        return users;
    }
    
    public static ArrayList<Course>getCourses(){
        
        return courses;
    }
    
}
