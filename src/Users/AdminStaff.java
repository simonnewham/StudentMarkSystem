/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Courses.Course;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    
   public static void addCourse(String courseName, String year, String convenor,String lecturer) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        
        String insertCourse = "INSERT INTO `users`.`courses` (`course_id`, `convenorname`, `courseyear`, `lecturer`) VALUES ('" + courseName + "', '" + convenor +"', '" + year + "', '" + lecturer + "')";
        
        myStatement.executeUpdate(insertCourse);
    }
    
}
