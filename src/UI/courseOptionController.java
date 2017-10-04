/*
 * Contoller responsible for handling events for the courseOption view
 * Used by convenor only for their course as well as adminstaff for any available course
 * Each user has options of how to interact with the course
 */
package UI;

import Courses.CourseClicked;
import Users.CurrentUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/*
 * CSC3003S Capstone
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class courseOptionController implements Initializable {
    
    @FXML private StackPane OptionsContent;
    @FXML private Button editMarks; 
    @FXML private Button markoverview; 
    @FXML private Button viewStudents; 
    @FXML private Button viewMarks; 
    @FXML private Button courseDetails; 
    
    @FXML Label title;
    
    /*Individual marks can be edited for a specific test or assignment*/
    @FXML
    public void handleEdit(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("EditMarks.fxml")));
        
     }
    
    /*Changes the stack pane to view the marks that exist in the database*/
    @FXML
    public void handleviewMarks(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarks.fxml")));
        
     }
    
    /*Changes the stack pane to view the students that exist in the database*/
    @FXML
    public void handleviewStudents(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));
        
     }
   
    
    /*Changes the stack pane to view the marks that exist in the database*/
     @FXML
    public void handleOverview(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));
        
     }
    
    /*
     * Checks if the view is being accessed by an AdminStaff or convenor and sets the label to the approriate course name
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(CurrentUser.getUserRole().equals("AS")){
             //only edit this
             title.setText(CourseClicked.getCourse());
        }
        else if (CurrentUser.getUserRole().equals("CC")){
             title.setText(UI.EditCourseController.course);
        }
    }    
    
}
