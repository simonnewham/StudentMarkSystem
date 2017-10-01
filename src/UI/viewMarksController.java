/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Users.CurrentUser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class viewMarksController implements Initializable {
    
    @FXML TableView table;
    @FXML Button searchSN;
    @FXML Button searchCC;
    @FXML TextField SN;
    @FXML TextField CC;
    
    public ObservableList<ObservableList> data = FXCollections.observableArrayList(
            
     );
    
    @FXML
    public void getStudent() throws FileNotFoundException, IOException, SQLException{
        
        
        String search = SN.getText();
        
        if(!search.equals("")){
            //data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
             Statement myStatement = myConn.createStatement();
             ResultSet rs = myStatement.executeQuery("SELECT * FROM users.marks \n"
                                               + "WHERE users.marks.studentname = '"+search.trim()+"'");
            
             this.getData_and_Set(rs);
             myConn.close();
            }
        else{
            this.getMarks();
        }
        SN.clear();
     }
    
    @FXML
    public void getCourse() throws FileNotFoundException, IOException, SQLException{
        
        String course = CC.getText();
        
         if(!course.equals("")){
            //data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
             Statement myStatement = myConn.createStatement();
             ResultSet rs = myStatement.executeQuery("SELECT * FROM users.marks \n"
                                               + "WHERE users.marks.coursename = '"+course.trim()+"'");
            
             this.getData_and_Set(rs);
             myConn.close();
            }
        else{
            this.getMarks();
        }
         CC.clear();
     }
    
    @FXML 
    public void getData_and_Set(ResultSet RS) throws FileNotFoundException, IOException, SQLException{
        
        data.clear();
        ResultSet rs = RS;
        table.getColumns().clear();
        
        //add columns
         for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
             //We are using non property style for making dynamic table
            final int j = i;  
         
             TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
         
             col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                             
                     return new SimpleStringProperty(param.getValue().get(j).toString());                       
                 }                   
             });
             table.getColumns().addAll(col);
    
         }
         //add data          
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            table.setItems(data);
        
    }
   
    @FXML
    public void getMarks() throws FileNotFoundException, IOException, SQLException{
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM users.marks");
        
        this.getData_and_Set(rs);
        myConn.close();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String user = CurrentUser.getUserName();
        String role = CurrentUser.getUserRole();
        System.out.println(role);
        if(role.equals("S")){
            SN.setText(user);
            SN.setDisable(true);
            CC.setDisable(true);
            searchCC.setDisable(true);
        }
        
    }    
    
}
