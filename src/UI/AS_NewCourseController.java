package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class AS_NewCourseController implements Initializable {

    /**
     * Initializes the controller class
     */
    
    public String coursecode;
    @FXML private TextField courseName;
    @FXML private TextField courseYear;
     @FXML private TextField fileName;
    @FXML private Button addCourse;
    @FXML private MenuButton convenorButton;
    //@FXML private MenuButton lectureButton;
    
    public String convenorName;
    //public String lecturerName;
    
     
    @FXML   
     public void handleConvenor(ActionEvent event) throws IOException, SQLException{
       
     }
     
    @FXML   
     public void handleAddCourse(ActionEvent event) throws IOException, SQLException{
         
         String changeRole = "UPDATE `users`.`users_login` SET `role`='CC' WHERE `user_id`='" + convenorName + "'";
         Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96");
         Statement myStatement = myConn.createStatement();
         myStatement.executeUpdate(changeRole);
         myConn.close();
         coursecode=courseName.getText();
         Users.AdminStaff.addCourse(courseName.getText(), courseYear.getText(),convenorName);
         courseName.clear();
         courseYear.clear();
         convenorButton.setText("Select");
       //  lectureButton.setText("Select");
     }
     
      @FXML   
     public void handleAddStudents(ActionEvent event) throws IOException, SQLException{
         Users.AdminStaff.importStudents(fileName.getText(), coursecode);
         fileName.clear();
     }
     
      @FXML   
     public void handleConvenorButton(ActionEvent event) throws IOException, SQLException{
         convenorButton.getItems().add(new MenuItem("test1"));
         convenorButton.getItems().add(new MenuItem("test2"));
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            convenorButton.setText("Select");
            convenorButton.getItems().clear();
            
            String selectConvenors = "SELECT * FROM users.users_login WHERE role = 'L'";
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96");
            Statement myStatement = myConn.createStatement();
            ResultSet myRSC = myStatement.executeQuery(selectConvenors);
            
            while( myRSC.next()){
                MenuItem item = new MenuItem(myRSC.getString("user_id"));
                item.setOnAction(a->{
                convenorName = item.getText();
                convenorButton.setText(convenorName);
                });
                convenorButton.getItems().add(item);
            }
            
            
            myConn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AS_NewCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*try {
            lectureButton.setText("Select");
            lectureButton.getItems().clear();
            
            String selectLecturers = "SELECT * FROM users.users_login WHERE role = 'L'";
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96");
            Statement myStatement = myConn.createStatement();
            ResultSet myRSL = myStatement.executeQuery(selectLecturers);


            while( myRSL.next()){
                MenuItem item = new MenuItem(myRSL.getString("user_id"));
                item.setOnAction(a->{
                    lecturerName = item.getText();
                    lectureButton.setText(lecturerName);
                });
                lectureButton.getItems().add(item);
            }

            myConn.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AS_NewCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }    
    
}

