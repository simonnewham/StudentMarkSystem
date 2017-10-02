/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Courses;

/**
 *
 * @author simonnewham
 */
public class CourseClicked {
    
    static String course;
    static Boolean clicked=false;
    
    public static void setCourseClicked(String c){
        
        course =c;
        clicked =true;
        
    }

    public static String getCourse() {
        return course;
    }

    public static Boolean getClicked() {
        return clicked;
    }
    
    public static void Clear(){
        course=null;
        clicked=false;
    }
    
}
