/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Courses.CourseClicked;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class AdminStaffHomeController implements Initializable {

   @FXML private Button signout;    
   @FXML private StackPane content;
   @FXML private ImageView logo;
   @FXML Button viewStudents;
   @FXML Button viewCourses;
   @FXML Button newCourse;
   @FXML Button viewMarks;
   
   @FXML
   public void handleSignOut(ActionEvent event) throws IOException{
       
       Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Scene home_scene = new Scene(home_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
       app_stage.setScene(home_scene);
       app_stage.show(); 
       CourseClicked.Clear();
   }
   
   @FXML
   public void hanldeviewCourses()throws IOException{
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewCourses.fxml")));
       
   }
   
   @FXML
   public void hanldeviewStudents()throws IOException{
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));
       
   }
   @FXML
   public void hanldeNewCourse()throws IOException{
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("AS_NewCourse.fxml")));
       
   }
   
   @FXML
   public void hanldeMarks()throws IOException{
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarks.fxml")));
       
   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          //load logo
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }    
    
}
