/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Courses.*;
import Users.Student;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class viewMarksTController implements Initializable {
    
    @FXML private TableView<MarkLayer1> table;
    @FXML TableColumn<MarkLayer1, String> stuNum;
    @FXML TableColumn<MarkLayer1, String> course;
    @FXML TableColumn <MarkLayer1, Integer> classM;
    @FXML TableColumn <MarkLayer1, String> exam;
    @FXML TableColumn <MarkLayer1, String> finalM;
    
     public ObservableList<MarkLayer1> data = FXCollections.observableArrayList(
            
     );
    
    
    public void getMarks() throws FileNotFoundException, IOException, SQLException{
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        
        ResultSet rs = myStatement.executeQuery("Select * FROM users.marks");
        
        this.getData_and_Set(rs);
        
        myConn.close();
        
        
      
    }
    
     @FXML 
    public void getData_and_Set(ResultSet RS) throws FileNotFoundException, IOException, SQLException{
        
        data.clear();
        ResultSet rs = RS;
        //table.getColumns();
      
         //add data 
         while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                System.out.println("Row [1] added "+row );
               // data.add(row);
                
                //work out class mark
                int total=0;
                int tests=0;
                int avg=0;
                 for(int j=4; j<row.size(); j++){
                    String mark = row.get(j).toString();
                    
                    total = total+ Integer.parseInt(mark);
                    //System.out.print(total);
                    tests=j-3;
             
                }
                if (tests !=0){
                    avg = total/tests;
                }
                System.out.println(avg);
                
                //work out final mark
                String finalM= "-";
                
                if(rs.getString("Exam")!=null){
                    int exam = Integer.parseInt(rs.getString("Exam"));  
                    finalM= String.valueOf((avg+exam)/2);
                    
                }
               
                data.add(new MarkLayer1(
                        
                    rs.getString("studentname"),
                    rs.getString("coursename"),
                    avg,
                    finalM,
                    rs.getString("Exam")

                ));

            }
         
           table.setItems(data);
          
         
         
         
            //working out the average of the entered marks for each student
//            for(int i=0; i< data.size(); i++){
//                
//                int total=0;
//                int tests=0;
//                int avg=0;
//                
//                for(int j=5; j<data.get(i).size(); j++){
//                    String mark = data.get(i).get(j).toString();
//                    
//                    total = total+ Integer.parseInt(mark);
//                    //System.out.print(total);
//                    tests=j-4;
//             
//                }
//                if (tests !=0){
//                    avg = total/tests;
//                }
//                
//                System.out.println(avg);
//            }
            
             
            
            
            //FINALLY ADDED TO TableView
            //table.setItems(data);
        
    }
    
     @FXML 
    public void handleSelected() throws Exception{
        
        TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = table.getItems().get(index).toString();
        //selected = selected.substring(1, selected.indexOf(",")); //only get username
        System.out.println(selected);   
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        stuNum.setCellValueFactory(new PropertyValueFactory<> ("stunum"));
        course.setCellValueFactory(new PropertyValueFactory<> ("coursename"));
        classM.setCellValueFactory(new PropertyValueFactory <>("classM"));
        exam.setCellValueFactory(new PropertyValueFactory <>("exam"));
        finalM.setCellValueFactory(new PropertyValueFactory <>("finalM"));
        
        
        try{
                this.getMarks();
            }
            catch (IOException | SQLException e) {

            }
            
        
        
    }    
    
}
