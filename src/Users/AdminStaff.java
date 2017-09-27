/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Courses.Course;
import java.util.ArrayList;

/**
 *
 * @author simonnewham
 */
public class AdminStaff extends User{
    
    private ArrayList<Course> all_courses;
    
     public AdminStaff(String u,String f, String l,  String e, String p, String r){
        
        super(u, f, l, e, p, r);
       
    }
     
    public ArrayList<Course> displayAll(){
        
        return all_courses;
    }
    
    //edit marks
    
    //view marks
    
}
