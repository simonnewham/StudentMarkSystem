/*
 * Contoller class responsible for the editMark view for all users
 */
package UI;

import Courses.CourseClicked;
import Users.CurrentUser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * CSC3003S CAPSTONE
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class EditCourseController implements Initializable {
    
   public static String course;
            
   @FXML private Button importButton; 
   @FXML private Button editButton;
    
   @FXML private TextField assName;   
   @FXML private TextField fileName;
   @FXML private TextField studentNumber;
   @FXML private TextField testName;   
   @FXML private TextField testMark;
   
   /*Passes through the current course of that the course convenor is convening*/
   public static void setCourse(String coursecode){
       course = coursecode;
   }
   
   /*Imports a list the marks of a single assessment of test for a list of students from a .csv file*/
    @FXML
    public void handleImportMarks() throws IOException, FileNotFoundException, SQLException{
        Users.Convenor.importMarks(course, fileName.getText(), assName.getText().toLowerCase());
        fileName.clear();
        assName.clear();
               
    }
    
    /*Edits an individual mark of an individual student user the assessment or test name and the 
    students username*/
    @FXML
    public void handleEditMark() throws SQLException{
        Users.Convenor.editMarks(course, studentNumber.getText(), testName.getText().toLowerCase(), testMark.getText());
        studentNumber.clear();
        testName.clear();
        testMark.clear();
    }
    
    /**
     * The AdminStaff can only access the edit marks via the viewCourses view
     * The course they clicked on is stored in the static CourseCliked class
     * Therefore the method check if the user is an AS to load the course for them
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(CurrentUser.getUserRole().equals("AS")){
            course = CourseClicked.getCourse();
        }

        
        
    }    
    
}
