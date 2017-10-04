/*
 * Contoller responsible for handling events for the convenorHome framework
 */
package UI;

import Users.CurrentUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class convenorController implements Initializable {

   @FXML private StackPane content;  
   
   @FXML Button viewStudents;
   @FXML Button viewCourses;
   @FXML Button searchMarks;
   @FXML private Button course;
   @FXML private Button signout;
   @FXML private StackPane EditContent;
   @FXML private StackPane EditView;
   @FXML private ImageView logo;
   @FXML Label welcome;
        
     
    /*Signs the convenor out and changes the view back to the log in page*/
     @FXML   
     public void handleSignOut(ActionEvent event) throws IOException{
        UI.EditCourseController.course =null;
         Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
         Scene home_scene = new Scene(home_parent);
         Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
         app_stage.setScene(home_scene);
         app_stage.show();
    
    }
     
     /*Allows the conenor to import marks, edit them, and view marks and students from the course that the convenor is convening*/
    @FXML   
     public void handleCourse(ActionEvent event) throws IOException, SQLException{
        UI.EditCourseController.course =null; 
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96");
        Statement myStatement = myConn.createStatement();
        String findCourse = "SELECT * FROM users.courses WHERE convenorname = '"+CurrentUser.getUserName()+"'";
        ResultSet myRS = myStatement.executeQuery(findCourse);
        while (myRS.next()){
        UI.EditCourseController.setCourse(myRS.getString("course_id"));
        }
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("courseOption.fxml")));
         
     } 
     
     /*Changes the stack pane to view the students that exist in the database*/
     @FXML   
     public void handleViewAll(ActionEvent event) throws IOException{
        UI.EditCourseController.course =null;
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));

     }
     
     /*Changes the stack pane to view the courses that exist in the database*/
     @FXML   
     public void handleCourses(ActionEvent event) throws IOException{
        UI.EditCourseController.course =null;
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewCourses.fxml")));
         
     }
     
     /*Changes the stack pane to view overview marks that exist in the database*/
     @FXML   
     public void handleMarks(ActionEvent event) throws IOException{
        UI.EditCourseController.course =null;
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));
        
         
     }
      
    /*
     * Initializes the controller class by welcoming convenor and loading UCT logo.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //load logo
        System.out.println(CurrentUser.getUserName());
        welcome.setText("Welcome:\n"+CurrentUser.getUserName()+"\n(Convenor)");
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        
    }    
    
}
