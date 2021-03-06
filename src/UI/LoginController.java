/*
 * Controller responsilbe for loading and handling user logins
 */
package UI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Users.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * CSC3003S Capstone
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class LoginController implements Initializable {
    
   @FXML
   private Button login;
   @FXML
   private Text error;
   @FXML
   private PasswordField passwordField;
   @FXML
   private TextField username;
   @FXML
   private ImageView logo;
   
   /*
    * Method is responsible for establishing connecting with users_login table
    * The username and password are compared for a corresponding pair in the database
    * If there is a match the user is granted access to the correct view corresponding with their role
   */
   @FXML
   public void handleLogin(ActionEvent event) throws IOException, SQLException{
       
       //check credentials
       String u = username.getText();
       String p = passwordField.getText();
       
  
       Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
       Statement myStatement = myConn.createStatement();
       ResultSet myRltSet = myStatement.executeQuery("select * from users_login");
       
       while (myRltSet.next()){
          if (u.equals(myRltSet.getString("user_id")) && p.equals(myRltSet.getString("password"))){
               
              //set current user to username entered
              CurrentUser.setUserName(u);
              CurrentUser.setUserRole(myRltSet.getString("role"));
              
               System.out.println("Welcome "+ myRltSet.getString("first_name") + " " + myRltSet.getString("surname"));
               
                        //if convenor
                        if(myRltSet.getString("role").equals("CC")){
                            
                           Parent home_parent = FXMLLoader.load(getClass().getResource("convenorHome.fxml"));
                           Scene home_scene = new Scene(home_parent);
                           Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                       
                           app_stage.hide();
                           app_stage.setScene(home_scene);
                           //app_stage.setTitle("CC view");
                           app_stage.show();
                        }
                        //if student
                        else if(myRltSet.getString("role").equals("S")){
                            
                            Parent home_parent = FXMLLoader.load(getClass().getResource("Student_view.fxml"));
                            Scene home_scene = new Scene(home_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                            app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();    
                        }
                        else if(myRltSet.getString("role").equals("L")){
                            
                            Parent home_parent = FXMLLoader.load(getClass().getResource("Lecturer_view.fxml"));
                            Scene home_scene = new Scene(home_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                            app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();    
                        }
                        else if(myRltSet.getString("role").equals("A")){
                            Parent home_parent = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
                            Scene home_scene = new Scene(home_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                                        //app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();   
                        }
                        else if(myRltSet.getString("role").equals("AS")){
                            Parent home_parent = FXMLLoader.load(getClass().getResource("AdminStaffHome.fxml"));
                            Scene home_scene = new Scene(home_parent);
                             Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                             //app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();   
                        }
            
            }
            else{
                error.setText("Error with Login");      
            }  
       } 
       myConn.close();
   }
    
    @Override
    /*
     * Load the UCT logo when Login is loaded
    */
    public void initialize(URL url, ResourceBundle rb) {
        //load logo
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }    
    
}
