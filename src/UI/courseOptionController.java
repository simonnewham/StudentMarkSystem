/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class courseOptionController implements Initializable {
    
    @FXML private StackPane OptionsContent;
    @FXML private Button editMarks; 
    @FXML private Button editCourse; 
    @FXML private Button viewStudents; 
    @FXML private Button viewMarks; 
    @FXML private Button courseDetails; 
    
    @FXML Label title;
    
    @FXML
    public void handleEdit(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("EditMarks.fxml")));
        
     }
    
    @FXML
    public void handleviewMarks(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarks.fxml")));
        
     }
    
    @FXML
    public void handleviewStudents(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));
        
     }
    @FXML
    public void handleEditMarks(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("")));
        
     }
    
     @FXML
    public void handleEditCourse(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("")));
        
     }
    
    /**
     * Initializes the controller class.
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
