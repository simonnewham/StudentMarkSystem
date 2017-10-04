/*
 * Controller responsible for laoding all the availabe courses found within the database
 * October 2017
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
 * CSC3003S CAPSTONE
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class viewCoursesController implements Initializable {
    
    @FXML TableView table;
    @FXML StackPane content;
    
    //List stores the rows that will be displayed
    public ObservableList<ObservableList> data = FXCollections.observableArrayList(
            
     );
    
    String user;
    String role;
    
    /*
     * Method access the database and creates the columns for the table based on column names in the database
     * Data is then loaded row by row and the tableItems are displayed to the user
    */
    @FXML
    public void getCourses() throws FileNotFoundException, IOException, SQLException{
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM users.courses");
        
        //add columns
         for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
            
            final int j = i;  
         
             TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
         
             col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                             
                     return new SimpleStringProperty(param.getValue().get(j).toString());                       
                 }                   
             });
             table.getColumns().addAll(col);
         }
         
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
    /*
     * Method only accessable by adminStaff
     * Allows admin staff to click on a row and edit the selected course via the CourseOption view.
    */
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
    /*
     * Keeps track of current user and intiates the method to get all available courses
    */
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