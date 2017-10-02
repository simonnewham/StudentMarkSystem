/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class AdminDeleteController implements Initializable {
    
    @FXML private TextField removeUsername;
    
    @FXML
    public void handleRemoveUser() throws SQLException{
        Users.Admin.handleRemoveUser(removeUsername.getText());
        removeUsername.clear();
    }
    
    @FXML
    public void handleCancel() throws SQLException{
        removeUsername.clear();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
