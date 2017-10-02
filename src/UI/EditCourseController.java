/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class EditCourseController implements Initializable {

   @FXML private Button importButton; 
   @FXML private Button editButton;
   //@FXML private Button back;
    
   @FXML TextField assName;   
   @FXML private TextField fileName;
   @FXML private TextField studentNumber;
   @FXML private TextField testName;   
   @FXML private TextField testMark;
   
    @FXML
    public void handleImportMarks() throws IOException, FileNotFoundException, SQLException{
        Users.Convenor.importMarks(fileName.getText());
        fileName.clear();
               
    }
    @FXML
    public void handleEditMark() throws SQLException{
        Users.Convenor.editMarks(studentNumber.getText(), testName.getText(), testMark.getText());
        studentNumber.clear();
        testName.clear();
        testMark.clear();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
