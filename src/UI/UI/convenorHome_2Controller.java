/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class convenorHome_2Controller implements Initializable {

  
    /**
     * Initializes the controller class.
     */
     @FXML
   private Button importButton;
     
      @FXML
   private Button editButton;
      
      @FXML
   private TextField fileName;
    
       @FXML
   private TextField studentNumber;
       
        @FXML
   private TextField testName;
        
         @FXML
   private TextField testMark;
         @FXML
    private ImageView image;
         
    public void handleImportMarks() throws IOException{
        Users.Convenor.importMarks(fileName.getText());
        fileName.clear();
               
    }
    
    public void handleEditMark(){
        Users.Convenor.editMarks(studentNumber.getText(), testName.getText(), Integer.parseInt(testMark.getText()));
    }
    
    public void handleBack(ActionEvent event) throws IOException{
        
         Parent home_parent = FXMLLoader.load(getClass().getResource("convenorHome_1.fxml"));
         Scene home_scene = new Scene(home_parent);
         Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //current stage
         app_stage.hide();
         app_stage.setScene(home_scene);
         app_stage.show();
    }
    
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }
}
