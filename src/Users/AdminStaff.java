/*
 * Class responsible for handling all actions taken by the admin staff role
 */
package Users;

import Courses.Course;
import UI.AS_NewCourseController;
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
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class AdminStaff extends User{
    
    private ArrayList<Course> all_courses;
    
     public AdminStaff(String u,String f, String l,  String e, String p, String r){
        
        super(u, f, l, e, p, r);
       
    }
     
    public ArrayList<Course> displayAll(){
        
        return all_courses;
    }
    
    /*Adds and individual course to the database and changes the role of a lecturer to a convenor when a convenor
    is assigned to the course.
    Gives an error message if the course trying to be added already exists*/
   public static void addCourse(String courseName, String year, String convenor) throws SQLException{
        String courseNameUpper = courseName;
        String courseNameLower =  courseName.toLowerCase();
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        try{
            String markTable ="CREATE TABLE `users`.`"+courseNameLower+"_marks` (`studentname` VARCHAR(9) NOT NULL,PRIMARY KEY (`studentname`))";
            String insertCourse = "INSERT INTO `users`.`courses` (`course_id`, `convenorname`, `courseyear`) VALUES ('" + courseNameUpper + "', '" + convenor +"', '" + year + "')";
        
             myStatement.executeUpdate(markTable);
            myStatement.executeUpdate(insertCourse);
        }
        catch(SQLException e){
            System.out.println("ERROR! Course already exists");
            AS_NewCourseController.Error("ERROR! Course already exists");
        }
        myConn.close();
    }
   
   /*Reads through a .csv file and imports a list of students on the file.
   The students are added to a participants table in the database and to the courses mark sheet of the course 
   that has just been created.*/
   public static void importStudents(String fileName,String courseCode) throws SQLException, FileNotFoundException{
       String courseCodelower = courseCode.toLowerCase();
       Scanner s = new Scanner(new File(fileName));
       Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
       Statement myStatement = myConn.createStatement();
       
       while (s.hasNext()){
           String tempStudent = s.next();
           try{
                String markTableInsert = "INSERT INTO `users`.`"+courseCodelower+"_marks` (`studentname`) VALUES ('"+tempStudent+"');";
                String participantsInsert ="INSERT INTO `users`.`participants` (`course_id`, `student_id`, `courses`) VALUES ('"+tempStudent+courseCode+"', '"+tempStudent+"', '"+courseCode+"');";
                myStatement.executeUpdate(markTableInsert);
                myStatement.executeUpdate(participantsInsert);
           }
           catch(SQLException e){
               System.out.println(tempStudent+" could not be imported");
               AS_NewCourseController.Error("ERROR! Users not imported");
           }
       }
       
   }
    
}
