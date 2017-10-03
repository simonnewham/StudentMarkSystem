/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Users.CurrentUser;
import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class convenorController implements Initializable {

    
   @FXML private StackPane content;  
   @FXML private StackPane OptionsContent;
   
   @FXML Button viewStudents;
   @FXML Button viewCourses;
   @FXML Button searchMarks;
   @FXML private Button course;
   @FXML private Button signout;
    
   @FXML private StackPane EditContent;
    
  
   @FXML private StackPane EditView;
  
   @FXML private ImageView logo;
   
   
//   @FXML private Button importButton; 
//   @FXML private Button editButton;
//   @FXML private Button back;
//    
//   @FXML private Button editMarks;    
//   @FXML private TextField fileName;
//   @FXML private TextField studentNumber;
//   @FXML private TextField testName;   
//   @FXML private TextField testMark;
   
        
     //HOME
     @FXML   
     public void handleSignOut(ActionEvent event) throws IOException{
        
         Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
         Scene home_scene = new Scene(home_parent);
         Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
         app_stage.setScene(home_scene);
         app_stage.show();
    
    }
    @FXML   
     public void handleCourse(ActionEvent event) throws IOException, SQLException{
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
     
     @FXML   
     public void handleViewAll(ActionEvent event) throws IOException{
        
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewStudents.fxml")));

     }
     
     @FXML   
     public void handleCourses(ActionEvent event) throws IOException{
        
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewCourses.fxml")));
         
     }
     
     @FXML   
     public void handleMarks(ActionEvent event) throws IOException{
        
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarks.fxml")));
        
         
     }
     
    //OPTIONS
    @FXML
    public void handleEdit(ActionEvent event) throws IOException{
       
        OptionsContent.getChildren().clear();
        OptionsContent.getChildren().add(FXMLLoader.load(getClass().getResource("convenor_EditMarks.fxml")));
        
    }
         
   //HANDLE MARKS
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //load logo
        System.out.println(CurrentUser.getUserName());
        //welcome.setText("Welcome "+CurrentUser.getUserName());
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
        
    }    
    
}
