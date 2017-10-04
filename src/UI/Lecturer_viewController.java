/*
 * Controller responsible for loading and handling events in the lecture view framework
 */
package UI;

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
 *CSC3003S CAPSTONE
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */

public class Lecturer_viewController implements Initializable {

   @FXML private Button signout;
   @FXML Label welcome;
   @FXML private Button Viewmarks;
  
   @FXML ImageView logo;
     
   @FXML private Button Viewstudents;
   
   @FXML Button currentCourse;
      
   @FXML private StackPane content;
   
      
   /*signs the user out and changes the view to the log in page*/
   @FXML
   public void handleSignOut(ActionEvent event) throws IOException{
       
       Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Scene home_scene = new Scene(home_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
       app_stage.setScene(home_scene);
       app_stage.show();  
   }
   
   /*Allows the lecturer to view all the marks that are on the system*/
   @FXML
   public void handleViewMarks(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));

   }
   
   /*Allows the lecturer to get information from the current course the lecturer is lecturing in*/
   @FXML
   public void handleCurrentCourse(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("courseOption.fxml")));
       
   }
   
   /*Allows the lecturer to view all the students in the system*/
   @FXML
   public void handleViewStudents(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));
       
   }
    /*
     * Welcomes the lecturer and loads the UCT logo
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(CurrentUser.getUserName());
        welcome.setText("Welcome \n"+CurrentUser.getUserName());
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }    
    
}
