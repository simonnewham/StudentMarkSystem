/*
 * Controller responsible for displaying students in the viewStudents table
 */
package UI;

import Courses.CourseClicked;
import Users.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * CSC3003S CAPSTONE
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class viewStudentsController implements Initializable {
    
    @FXML GridPane grid;
    @FXML private TableView<Student> table;
    @FXML TableColumn<Student, String> user_id;
    @FXML TableColumn<Student, String> first_name;
    @FXML TableColumn<Student, String> surname;
    @FXML TableColumn<Student, String> course;
    //@FXML TableColumn<Student, String> email;
    
    public ObservableList<Student> data = FXCollections.observableArrayList(
            
        );
   
    @FXML Button searchSN;
    @FXML Button searchCC;
    @FXML TextField SN;
    @FXML TextField CC;
    @FXML TextField file;
    @FXML Button export;
    @FXML Label msg;
    
    /*
    * Method invoked when a user searches for a particular student number 
    * A student object is created from a result set for that student
    * The results are then displayed 
    */
    @FXML
    public void getStudent() throws FileNotFoundException, IOException, SQLException{

        String search = SN.getText();
        SN.clear();
        
        if(!search.equals("")){
            data.clear();
            
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT participants.student_id, participants.courses, users_login.first_name, users_login.surname, users_login.email"
                     + " FROM users.participants, users.users_login\n" +
                        " WHERE participants.student_id = users_login.user_id AND participants.student_id='"+search+"'");

           while (rs.next()){
            data.add(new Student(
                    rs.getString("student_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    //rs.getString("email"),
                    rs.getString("courses")

            ));
           
        }
           table.setItems(data);
           myConn.close();
        }
        else{
           this.getStudents();
        }
       
    }
    /*
    *Method loads all students within the searched course and displays the content 
    */
    @FXML
    public void getCourse() throws FileNotFoundException, IOException, SQLException{
        
        String course = CC.getText();
        CC.clear();
        
        if(!course.equals("")){
            data.clear();
            
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT participants.student_id, participants.courses, users_login.first_name, users_login.surname, users_login.email"
                     + " FROM users.participants, users.users_login\n" +
                        " WHERE participants.student_id = users_login.user_id AND participants.courses='"+course+"'");

           while (rs.next()){
            data.add(new Student(
                    rs.getString("student_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    //rs.getString("email"),
                    rs.getString("courses")

            ));
           
        }
           table.setItems(data);
           myConn.close();
        }
        else{
           this.getStudents();
        }
   
    }
    /*
    * Loads all students which are enrolled in a course
    */
    @FXML
     private void getStudents() throws FileNotFoundException, IOException, SQLException{
        
         
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT participants.student_id, participants.courses, users_login.first_name, users_login.surname, users_login.email\n" +
                                                "FROM users.participants, users.users_login \n" +
                                                "WHERE participants.student_id = users_login.user_id");
       
        while (rs.next()){
            data.add(new Student(
                    rs.getString("student_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    //rs.getString("email"),
                    rs.getString("courses")

            ));
     
        }
        table.setItems(data);
        myConn.close();
    } 
     /*
     *Handles the exporting of the displayed student table 
     *Each student obejct is written row by row to the .csv file
     */
    @FXML 
    public void handleExport() throws Exception{
        
        Writer writer = null;
        String filename = file.getText();
        file.clear();
        
            try {
                File file = new File(filename+".csv");
                writer = new BufferedWriter(new FileWriter(file));
                for (Student student : data) {

                    String text = student.getUsername()+";" + student.getFirst() + ";" + student.getLast()
                            + ";"+ student.getCourse()+"\n";

                    writer.write(text);
                    msg.setText(filename+".csv Exported Successfully");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                msg.setText("Error with export");
            }
            finally {

                writer.flush();
                writer.close();
            }   
            
    } 
    
    /*
     * Sets up the fixed colums for the table
     * Determines how the view has been accessed. If through the courseOption view only students from that specific view are shown
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        user_id.setCellValueFactory(new PropertyValueFactory<Student, String> ("username"));
        first_name.setCellValueFactory(new PropertyValueFactory<Student, String> ("first"));
        surname.setCellValueFactory(new PropertyValueFactory <Student, String>("last"));
        course.setCellValueFactory(new PropertyValueFactory <Student, String>("course"));
        //email.setCellValueFactory(new PropertyValueFactory <Student, String>("email"));
        
        //Accessed by AS
        if(CourseClicked.getClicked()){ //AS staff only
            
            CC.setText(CourseClicked.getCourse());
            try{
            this.getCourse();
            }
            catch (IOException | SQLException e) {
  
            }
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
        }
        //accessed by CC
        else if(UI.EditCourseController.course !=null){
            
             CC.setText(UI.EditCourseController.course);
            try{
                this.getCourse();
            }
            catch (IOException | SQLException e) {
  
            }
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
        }
        //when not accessed through courseOptions
        else{
            
            try{
                this.getStudents();
            }
            catch (IOException | SQLException e) {

            }
        }
        
    }    
    
}
