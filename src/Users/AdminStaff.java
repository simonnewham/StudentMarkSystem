/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Courses.Course;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

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
    
   public static void addCourse(String courseName, String year, String convenor) throws SQLException{
        String courseNameUpper = courseName;
        String courseNameLower =  courseName.toLowerCase();
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        String markTable ="CREATE TABLE `users`.`"+courseNameLower+"_marks` (`studentname` VARCHAR(9) NOT NULL,PRIMARY KEY (`studentname`))";
        String insertCourse = "INSERT INTO `users`.`courses` (`course_id`, `convenorname`, `courseyear`) VALUES ('" + courseNameUpper + "', '" + convenor +"', '" + year + "')";
        
        myStatement.executeUpdate(markTable);
        myStatement.executeUpdate(insertCourse);
        myConn.close();
    }
   
   public static void importStudents(String fileName,String courseCode) throws SQLException, FileNotFoundException{
       String courseCodelower = courseCode.toLowerCase();
       Scanner s = new Scanner(new File(fileName));
       Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
       Statement myStatement = myConn.createStatement();
       
       while (s.hasNext()){
           String tempStudent = s.next();
           String markTableInsert = "INSERT INTO `users`.`"+courseCodelower+"_marks` (`studentname`) VALUES ('"+tempStudent+"');";
           String participantsInsert ="INSERT INTO `users`.`participants` (`course_id`, `student_id`, `courses`) VALUES ('"+tempStudent+courseCode+"', '"+tempStudent+"', '"+courseCode+"');";
           myStatement.executeUpdate(markTableInsert);
           myStatement.executeUpdate(participantsInsert);
       }
       
   }
    
}
