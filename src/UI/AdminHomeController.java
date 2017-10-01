/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class AdminHomeController implements Initializable {
    
    @FXML private StackPane content;
    @FXML ImageView logo;
    @FXML private Button addUser;
    @FXML private Button deleteUser;
    @FXML private Button viewUsers;
    @FXML private Button signout;
   
     
    @FXML
   public void addUser(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("AdminAdd.fxml")));

   }
   
   @FXML
   public void handleAddUser(ActionEvent event) throws IOException{
       

   }
   
    @FXML
   public void deleteUser(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("AdminDelete.fxml")));

   }
   
   @FXML
   public void handleviewUsers(ActionEvent event) throws IOException{
       
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("Admin_allUsers.fxml")));
       

   }
   
   @FXML
   public void signOut(ActionEvent event) throws IOException{
       
       Parent home_parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
       Scene home_scene = new Scene(home_parent);
       Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
       app_stage.setScene(home_scene);
       app_stage.show();  

   }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(CurrentUser.getUserName());
        File file = new File("admin.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }    
    
}
