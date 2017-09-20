/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import DB.*;
import Users.*;


/**
 *
 * @author simonnewham
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
   
   @FXML
   public void handleLogin(ActionEvent event) throws IOException{
       
       //check credentials
       String u = username.getText();
       String p = passwordField.getText();
       
       //access DB
       //DataBase DB = new DataBase();
       ArrayList<User> users = DataBase.getUsers();
       
       //TRACE
       /*for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i).getUser());
            System.out.println(users.get(i).getPass());
       }*/
      
       
       //Check Credentials
       for (User temp : users) {
           
            if (u.equals(temp.getUser()) && p.equals(temp.getPass())){
                
               System.out.println("Welcome "+temp.getUser());
               
                        //if convenor
                        if(temp.getRole().equals("CC")){
                            
                           Parent home_parent = FXMLLoader.load(getClass().getResource("convenorHome_1.fxml"));
                           Scene home_scene = new Scene(home_parent);
                           Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                       
                           app_stage.hide();
                           app_stage.setScene(home_scene);
                           app_stage.setTitle("CC view");
                           app_stage.show();
                        }
                        //if student
                        else if(temp.getRole().equals("S")){
                            
                            Parent home_parent = FXMLLoader.load(getClass().getResource("Student_view.fxml"));
                            Scene home_scene = new Scene(home_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                            app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();    
                        }
                        else if(temp.getRole().equals("L")){
                            
                            Parent home_parent = FXMLLoader.load(getClass().getResource("Lecturer_view.fxml"));
                            Scene home_scene = new Scene(home_parent);
                            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
                            app_stage.hide();
                            app_stage.setScene(home_scene);
                            app_stage.show();    
                        }
            
            }
            else{
                error.setText("Error with Login");
            
            }
	}
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load logo
        File file = new File("logo.gif");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }    
    
}
