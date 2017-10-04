

package UI;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * Admin Delete Controller class
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class AdminDeleteController implements Initializable {
    
    @FXML private TextField removeUsername;
    
    /*Removes a currently existing user from the database*/
    @FXML
    public void handleRemoveUser() throws SQLException{
        Users.Admin.handleRemoveUser(removeUsername.getText());
        removeUsername.clear();
    }
    
    /*Clears all filled in text fields that arent used*/
    @FXML
    public void handleCancel() throws SQLException{
        removeUsername.clear();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
