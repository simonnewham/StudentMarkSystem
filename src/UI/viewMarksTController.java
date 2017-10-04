/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Courses.*;
import Users.CurrentUser;
import Users.Student;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class viewMarksTController implements Initializable {
    
    @FXML StackPane content;
    
    @FXML TableView<MarkLayer1> table;
    @FXML TableColumn<MarkLayer1, String> stuNum;
    @FXML TableColumn<MarkLayer1, String> course;
    @FXML TableColumn <MarkLayer1, Integer> classM;
    @FXML TableColumn <MarkLayer1, String> exam;
    @FXML TableColumn <MarkLayer1, String> finalM;
    
    @FXML Button searchSN;
    @FXML Button searchCC;
    @FXML TextField SN;
    @FXML TextField CC;
    
    @FXML TextField file;
    @FXML Button export;
    @FXML Label msg;
    
    //variable to determine what page to load when back pressed
    String lastclicked=null;
    
     public ObservableList<MarkLayer1> data = FXCollections.observableArrayList(
            
     );
   
     @FXML
    public void getCourse() throws FileNotFoundException, IOException, SQLException{
        
        lastclicked = "c";
        
        String course = CC.getText();
        String table1 = course.toLowerCase()+"_marks";
        System.out.println(table1);
         
         if(!course.equals("")){
            data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT * FROM users."+table1.toString());
            String currentcourse= course;                             
            
            this.getData_and_Set(rs,currentcourse);
            myConn.close();
             
            
            }
        else{
           // this.getMarks();
        }
         CC.clear();
     }
    
    @FXML
    public void getStudent() throws FileNotFoundException, IOException, SQLException{
        
        lastclicked ="s";
        
        String search = SN.getText();
        ArrayList<String> courses = new ArrayList<>();
        
        
        if(!search.equals("")){
            data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT users.participants.student_id, users.participants.courses FROM users.participants\n" +
                                                  "WHERE users.participants.student_id = '"+search+"'");
            while(rs.next()){
                courses.add(rs.getString("courses"));
                
            }
            System.out.println(courses.toArray().toString());
            //myConn.close();
   
            for(int i=0;i<courses.size(); i++){
               
                 String table1 = courses.get(i).toLowerCase()+"_marks";
                 ResultSet rs1 = myStatement.executeQuery("SELECT * FROM users."+table1+" WHERE studentname='"+search.toUpperCase()+"'");
                 String currentcourse =courses.get(i);
                 this.getData_and_Set(rs1, currentcourse);
                 //myConn.close();
                 
           }
            //System.out.println(data.get(1));
           
            myConn.close();
            
            }
        else{
            //this.getMarks();
        }
        SN.clear();
     }
    
    
     @FXML 
    public void getData_and_Set(ResultSet RS, String c) throws FileNotFoundException, IOException, SQLException{
        
        String currentcourse=c;
        //data.clear();
        ResultSet rs = RS;
       
      
         //add data 
         while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                System.out.println("Info in "+row );
                
                //work out if exam is included
                //if it is then we need to not include the last mark in the class mark
                int reduce=0;
                //work out class mark
                try{
                    if(rs.getString("exam")!=null){
                      System.out.println("EXAM INCLUDED");
                      reduce =1;
                    }
                }
                catch (SQLException e){
                    System.out.println("NO EXAM YET");
                    
                }
                int upto=row.size()-reduce;
                int total=0;
                int tests=0;
                int avg=0;
                for(int j=1; j<upto; j++){
                    
                    String mark = row.get(j).toString();
                    
                    total = total+ Integer.parseInt(mark);
                    //System.out.print(total);
                    tests=j;
             
                }
                if (tests !=0){
                    avg = total/tests;
                }
                System.out.println(avg);
                //Determine if exam added and calc final
                String examM="-";
                String finalM= "-";
                
                try{
                    if(rs.getString("exam")!=null){
                        
                    examM = rs.getString("exam");
                    int exam = Integer.parseInt(rs.getString("exam"));  
                    finalM= String.valueOf((avg+exam)/2);
                    examM = rs.getString("exam");
                 }
                      
                }
                catch (SQLException e){
                    System.out.println("NO EXAM YET");
                    examM = "-";
                }
 
                //add content
                data.add(new MarkLayer1(
                        
                    rs.getString("studentname"),
                    currentcourse,
                    avg,
                    finalM,
                    examM
    
                ));

            }
            
            table.setItems(data);
   
    }
    
     @FXML 
    public void handleSelected() throws Exception{
        
        TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = table.getItems().get(index).toString();
        String sn = selected.substring(0, selected.indexOf(","));
        String cc =selected.substring(selected.indexOf(",")+1, selected.length());
         
        StudentMarkClick.Activate(sn, cc, lastclicked);
        //switch to detail view
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarks.fxml")));
        
        //selected = selected.substring(1, selected.indexOf(",")); //only get username
        System.out.println(sn+"|"+cc);   
        
    }
    @FXML
    public void handleExport() throws Exception{
        
        Writer writer = null;
        String filename = file.getText();
        file.clear();
        
            try {
                File file = new File(filename+".csv");
                writer = new BufferedWriter(new FileWriter(file));
                for (MarkLayer1 mark : data) {

                    String text = mark.getStunum()+";" + mark.getCoursename() + ";" + mark.getClassM()
                            + ";"+ mark.getExam()+";"+mark.getFinalM()+"\n";

                    writer.write(text);
                    msg.setText("Export Success");
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
        
        stuNum.setCellValueFactory(new PropertyValueFactory<> ("stunum"));
        course.setCellValueFactory(new PropertyValueFactory<> ("coursename"));
        classM.setCellValueFactory(new PropertyValueFactory <>("classM"));
        exam.setCellValueFactory(new PropertyValueFactory <>("exam"));
        finalM.setCellValueFactory(new PropertyValueFactory <>("finalM"));
                
        String user = CurrentUser.getUserName();
        String role = CurrentUser.getUserRole();
        
        System.out.println(role);
        
        //only show marks for particular sudent
        if(role.equals("S")){
          
            SN.setText(user);
            SN.setVisible(false);
            CC.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);

            try{
               this.getStudent();
            }
            catch (IOException | SQLException e) {
                
            }

        }
        //show only for particular course
        //AS
        else if(CourseClicked.getClicked()==true){ //admin clicked on it
                      
            CC.setText(CourseClicked.getCourse());
            SN.setVisible(false);
            CC.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
            try{
                this.getCourse();
            }
            catch (IOException | SQLException e) {

            }
            
        }
        //CC
        else if (UI.EditCourseController.course !=null){
     
            CC.setText(UI.EditCourseController.course);
            System.out.println(UI.EditCourseController.course);
            SN.setVisible(false);
            CC.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
            try{
                this.getCourse();
            }
            catch (IOException | SQLException e) {

            }
            
        }
        //coming back from detail view
        else if(StudentMarkClick.getBack()){
            //searched for student
            if(StudentMarkClick.getLastclick().equals("s")){
                SN.setText(StudentMarkClick.getStunum());
                 try{
                    this.getStudent();
                }
                catch (IOException | SQLException e) {
                    
                }
            }
            //searched for course
            else{
                CC.setText(StudentMarkClick.getCourse());
                 try{
                    this.getCourse();
                }
                catch (IOException | SQLException e) {
                    
                }          
            }        
        }
        //CC or AS viewing overview
        else if (UI.EditCourseController.course !=null){
     
            CC.setText(UI.EditCourseController.course);
            System.out.println(UI.EditCourseController.course);
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
            try{
                this.getCourse();
            }
            catch (IOException | SQLException e) {

            }
            
        }
  
    }    
    
}
