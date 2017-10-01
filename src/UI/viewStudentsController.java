/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class viewStudentsController implements Initializable {
    
    @FXML private TableView<Student> table;
    @FXML TableColumn<Student, String> user_id;
    @FXML TableColumn<Student, String> first_name;
    @FXML TableColumn<Student, String> surname;
    @FXML TableColumn<Student, String> course;
    @FXML TableColumn<Student, String> email;
    
    public ObservableList<Student> data = FXCollections.observableArrayList(
            
        );
    
    
    @FXML Button searchSN;
    @FXML Button searchCC;
    @FXML TextField SN;
    @FXML TextField CC;
    @FXML TextField file;
    @FXML Button export;
    @FXML Label msg;
    
    @FXML
    public void getStudent() throws FileNotFoundException, IOException, SQLException{
        
        
        String search = SN.getText();
        
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
                    rs.getString("email"),
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
    
    @FXML
    public void getCourse() throws FileNotFoundException, IOException, SQLException{
        
        String course = CC.getText();
        
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
                    rs.getString("email"),
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
                    rs.getString("email"),
                    rs.getString("courses")

            ));
     
        }
        table.setItems(data);
        myConn.close();
    } 
     
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
                            + ";"+ student.getCourse()+";"+ student.getEmail()+"\n";

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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        user_id.setCellValueFactory(new PropertyValueFactory<Student, String> ("username"));
        first_name.setCellValueFactory(new PropertyValueFactory<Student, String> ("first"));
        surname.setCellValueFactory(new PropertyValueFactory <Student, String>("last"));
        course.setCellValueFactory(new PropertyValueFactory <Student, String>("course"));
        email.setCellValueFactory(new PropertyValueFactory <Student, String>("email"));
        
        
        try{
            this.getStudents();
        }
        catch (IOException | SQLException e) {
  
        }
        
    }    
    
}
