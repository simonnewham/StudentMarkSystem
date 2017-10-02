/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Courses.CourseClicked;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author simonnewham
 */
public class viewCoursesController implements Initializable {
    
    @FXML TableView table;
    @FXML StackPane content;
    
    public ObservableList<ObservableList> data = FXCollections.observableArrayList(
            
     );
    
    String user;
    String role;
    
    @FXML
    public void getCourses() throws FileNotFoundException, IOException, SQLException{
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM users.courses");
        
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
         
            /********************************
             * Data added to ObservableList *
             ********************************/
            
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

        table.setItems(data);
         myConn.close();
    }
    
    @FXML 
    public void handleSelected() throws Exception{
        
        if(role.equals("AS")){
            TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = table.getItems().get(index).toString();
            System.out.println(selected);   

            selected = selected.substring(1, selected.indexOf(",")); //only get username   
            CourseClicked.setCourseClicked(selected);
            
            content.getChildren().clear();
            content.getChildren().add(FXMLLoader.load(getClass().getResource("courseOption.fxml")));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        user = CurrentUser.getUserName();
        role = CurrentUser.getUserRole();
        
        try{
            this.getCourses();
        }
        catch (IOException | SQLException e) {
  
        }
    }      
    
}