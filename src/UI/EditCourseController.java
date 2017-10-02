/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * FXML Controller class
 *
 * @author simonnewham
 */
public class EditCourseController implements Initializable {
    String course;
            
   @FXML private Button importButton; 
   @FXML private Button editButton;
   //@FXML private Button back;
    
   @FXML private TextField assName;   
   @FXML private TextField fileName;
   @FXML private TextField studentNumber;
   @FXML private TextField testName;   
   @FXML private TextField testMark;
   
    @FXML
    public void handleImportMarks() throws IOException, FileNotFoundException, SQLException{
        Users.Convenor.importMarks(course, fileName.getText(), assName.getText());
        fileName.clear();
        assName.clear();
               
    }
    @FXML
    public void handleEditMark() throws SQLException{
        Users.Convenor.editMarks(course, studentNumber.getText(), testName.getText(), testMark.getText());
        studentNumber.clear();
        testName.clear();
        testMark.clear();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(CurrentUser.getUserRole().equals("AS")){
            course = CourseClicked.getCourse();
        }
        else{//convenor
            course = "CSC3003S";
        }
        //System.out.println(course);
    }    
    
}
