/*
 * TController responsible for handling events in the AdminStaff home framework
 */
package UI;

import Courses.CourseClicked;
import Users.CurrentUser;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * CSC3003S CapStone
 * NWHSIM001, GRNCAM007, WLLCOU004
 *
 */
public class AdminStaffHomeController implements Initializable {

   @FXML private Button signout;    
   @FXML private StackPane content;
   @FXML private ImageView logo;
   @FXML Button viewStudents;
   @FXML Button viewCourses;
   @FXML Button newCourse;
   @FXML Button viewMarks;
   @FXML Label welcome;
   
   /*Logs out the current user and changes the view back to the log in page*/
   @FXML
   public void handleSignOut(ActionEvent event) throws IOException{
       Courses.CourseClicked.Clear();
       UI.EditCourseController.course=null;
       Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Scene home_scene = new Scene(home_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
       app_stage.setScene(home_scene);
       app_stage.show(); 
       CourseClicked.Clear();
   }
   
   /*Changes the stack pane to view the courses that exist in the database*/
   @FXML
   public void hanldeviewCourses()throws IOException{
       UI.EditCourseController.course=null;
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewCourses.fxml")));
       
   }
   
   /*Changes the stack pane to view the students that exist in the database*/
   @FXML
   public void hanldeviewStudents()throws IOException{
       UI.EditCourseController.course=null;
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));
       
   }
   
   /*Allows the admin staff to create a new course*/
   @FXML
   public void hanldeNewCourse()throws IOException{
       UI.EditCourseController.course=null;
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("AS_NewCourse.fxml")));
       
   }
   
   /*Changes the stack pane to view the marks that exist in the database*/
   @FXML
   public void hanldeMarks()throws IOException{
       UI.EditCourseController.course=null;
       CourseClicked.Clear();
       content.getChildren().clear();
       content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));
       
   }
    /*
     * Initializes the controller class by loading the UCT logo.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        welcome.setText("Welcome:\n"+CurrentUser.getUserName()+"\n(Admin Staff)");
    }    
    
}
