/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class Admin_RoleController implements Initializable {

    @FXML  TextField firstname;
    @FXML  TextField surname;
    @FXML  TextField username;
    @FXML  TextField removeUsername;
    @FXML  TextField password;
    @FXML  TextField confirmPassword;
    @FXML  TextField fileName;
    @FXML  MenuButton roleButton;
    public String role;
    @FXML  Label msg;
    public static String errmsg="";
    
    @FXML
    public void handleAddUser() throws SQLException{
        errmsg="Import successfull";
        if (password.getText().equals(confirmPassword.getText())){
        Users.Admin.handleAddUser(firstname.getText(), surname.getText(), username.getText(), password.getText(), role);
        }
        
        firstname.clear();
        surname.clear();
        username.clear();
        password.clear();
        confirmPassword.clear();
        roleButton.setText("Role");
        
        msg.setText(errmsg);
    }
    
    
    @FXML
    public void handleRemoveUser() throws SQLException{
        Users.Admin.handleRemoveUser(removeUsername.getText());
        removeUsername.clear();
    }
    
    @FXML
    public void handleimportUser() throws SQLException, FileNotFoundException{
        errmsg="Import successfull";
        Users.Admin.handleImportUser(fileName.getText());
        fileName.clear();
        msg.setText(errmsg);
        
    }
    
    @FXML
    public void handleCancel() throws SQLException{
        removeUsername.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleButton.setText("Role");
        roleButton.getItems().clear();
        
        MenuItem itemA = new MenuItem("Admin");
        itemA.setOnAction(a->{
            role = "A";
            roleButton.setText("Admin");
            });
        roleButton.getItems().add(itemA);
        
        MenuItem itemAS = new MenuItem("Admin Staff");
        itemAS.setOnAction(a->{
            role = "AS";
            roleButton.setText("Admin Staff");
            });
        roleButton.getItems().add(itemAS);
        
//        MenuItem itemCC = new MenuItem("CourseConvenor");
//        itemCC.setOnAction(a->{
//            role = "CC";
//            roleButton.setText("Course Convenor");
//            });
//        roleButton.getItems().add(itemCC);
        
        MenuItem itemL = new MenuItem("Lecturer");
        itemL.setOnAction(a->{
            role = "L";
            roleButton.setText("Lecturer");
            });
        roleButton.getItems().add(itemL);
        
        MenuItem itemS = new MenuItem("Student");
        itemS.setOnAction(a->{
            role = "S";
            roleButton.setText("Student");
            });
        roleButton.getItems().add(itemS);
    
    }
    
    @FXML public static void Error(){
        
       errmsg ="Error Occured! Some users may already be in DB";
    }
    
}
