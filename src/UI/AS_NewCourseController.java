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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML New Course Controller class
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class AS_NewCourseController implements Initializable {

    /**
     * Initializes the controller class
     */
    @FXML Label msg;
    @FXML Label msg2;
    
    public String coursecode;
    @FXML private TextField courseName;
    @FXML private TextField courseYear;
     @FXML private TextField fileName;
    @FXML private Button addCourse;
    @FXML private MenuButton convenorButton;
    
    public String convenorName;
    public static String errmsg="";
    
    
    /*Adds a course to the courses table in the database and sets a new convenor and year to the course. 
     A current lecturer is turned into a course convenor.*/ 
    @FXML   
     public void handleAddCourse(ActionEvent event) throws IOException, SQLException{
        
         
         coursecode=courseName.getText();
         errmsg=coursecode+" Added";
         Users.AdminStaff.addCourse(courseName.getText(), courseYear.getText(),convenorName);
         
         String changeRole = "UPDATE `users`.`users_login` SET `role`='CC' WHERE `user_id`='" + convenorName + "'";
         Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96");
         Statement myStatement = myConn.createStatement();
         //if no error
         if(errmsg.equals(coursecode+" Added")){
             myStatement.executeUpdate(changeRole);
         }
         
         myConn.close();
        
         courseName.clear();
         courseYear.clear();
         convenorButton.setText("Select");
       //  lectureButton.setText("Select");
         msg.setText(errmsg);
     }
     
     /*Adds students to a particular course under the participants list.
     Imports the students in a .csv file */
      @FXML   
     public void handleAddStudents(ActionEvent event) throws IOException, SQLException{
         Users.AdminStaff.importStudents(fileName.getText(), coursecode);
         fileName.clear();
         //coursecode=null;
        
     }
     
      @FXML   
     public void handleConvenorButton(ActionEvent event) throws IOException, SQLException{
         convenorButton.getItems().add(new MenuItem("test1"));
         convenorButton.getItems().add(new MenuItem("test2"));
     }
    
     /*Adds all the current lecturers to the menu drop down tab and uses an action listener to determine which current lecturer
     is chosen.*/
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
    @FXML
    public static void Error(String err){
        errmsg=err;
    }
    
}

