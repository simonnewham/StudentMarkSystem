/*
 * Contoller class responsilbe for handling the correct loading for the second detailed layer of student marks
 * October 2017
 */
package UI;

import Courses.CourseClicked;
import Courses.StudentMarkClick;
import Users.CurrentUser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class viewMarksController implements Initializable {
    
    @FXML TableView table;
    @FXML Button searchSN;
    @FXML Button searchCC;
    @FXML TextField SN;
    @FXML TextField CC;
    
    @FXML Button back;
    @FXML StackPane content;
    
    @FXML TextField file;
    @FXML Button export;
    @FXML Label msg;
    
    public ObservableList<ObservableList> data = FXCollections.observableArrayList(
            
     );
    /*
    Method determines if back button was pressed and is used by ViewmarksT to load previous search
    */
    @FXML public void goBack() throws FileNotFoundException, IOException, SQLException{
        
        StudentMarkClick.setBack(true);
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));   
    }
    /*
    * Method is used to search for a particular student number but is not made use of
    */
    @FXML
    public void getStudent() throws FileNotFoundException, IOException, SQLException{
        
        data.clear();
        table.getColumns().clear();
        String search = SN.getText();
        ArrayList<String> courses = new ArrayList<>();
        table.getColumns().clear();
        
        if(!search.equals("")){
            data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            ResultSet rs = myStatement.executeQuery("SELECT users.participants.student_id, users.participants.courses FROM users.participants\n" +
                                                  "WHERE users.participants.student_id = '"+search.toUpperCase()+"'");
            while(rs.next()){
                courses.add(rs.getString("courses"));
                
            }
           
             myConn.close();
            }
        else{
            //this.getMarks();
        }
        SN.clear();
     }
    /*
    * Method used to load all the marks for the given course code searched for
    * users enter course code into CC textfield and press search
    *The method will find the table for that course and load the detailed mark.
    *Only accessed via courseOptions
    */
    @FXML
    public void getCourse() throws FileNotFoundException, IOException, SQLException{
        data.clear();
        table.getColumns().clear();
        table.getColumns().clear();
        String course = CC.getText();
        String table1 = course.toLowerCase()+"_marks";
        System.out.println(table);
         
         if(!course.equals("")){
            data.clear();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
             Statement myStatement = myConn.createStatement();
             ResultSet rs = myStatement.executeQuery("SELECT * FROM users."+table1+"");
                                              
            
             this.getData_and_Set(rs);
             myConn.close();
            }
        else{
           // this.getMarks();
        }
         CC.clear();
     }
    /*
    * Method takes in a result set and then creates column names for the table based on the result set
    * the info is then read and added to data list row by row
    *Finnaly the data is loaded into the table and displayed
    */
    @FXML 
    public void getData_and_Set(ResultSet RS) throws FileNotFoundException, IOException, SQLException{
        
        ResultSet rs = RS;
        
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
   
    /*
    * Method is used to load only details marks for a particular student in a particular course
    * It accesses the correct course database and creates a result set for that student
    */
    @FXML
    public void getDetails() throws FileNotFoundException, IOException, SQLException{
        
        String sn = SN.getText();
        String cc = CC.getText().toLowerCase()+"_marks";
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM users."+cc+" WHERE "+cc+".studentname ='"+sn+"'");
        
        this.getData_and_Set(rs);
        myConn.close();
    }
    
    /*
    * Method handles exporting the displayed table to a .csv file named by the user
    * This is done by calling the getContent method to read the displayed content
    * Then each row is written to the CSV with a message displayed if the write was successful
    */
    @FXML
    public void handleExport() throws Exception{
        
        Writer writer = null;
        String filename = file.getText();
        file.clear();
        
        ArrayList<ArrayList<String>> content = this.getContent();
        
            try {
                File file = new File(filename+".csv");
                writer = new BufferedWriter(new FileWriter(file));
                
                for (int j=0; j<content.size(); j++) {
                    String text="";
                        for(int i=0; i< content.get(j).size(); i++){
                            text=text+content.get(j).get(i)+";";  
                        } 
                   text.substring(0, text.length()-1);
                   text=text+"\n";
                   writer.write(text);
                }        
                   
                msg.setText(filename+".csv Exported");
                }
             catch (Exception ex) {
                ex.printStackTrace();
                msg.setText("Error with export");
            }
            finally {

                writer.flush();
                writer.close();
            }
   
    }
    /*
    *Method reads all the displayed content in the table requested by the user and returns the content
    */
    private ArrayList<ArrayList<String>> getContent(){
        
        ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
        ObservableList<TableColumn> columns = table.getColumns();
        
        for (Object row : table.getItems()) {
            ArrayList<String> value = new ArrayList<>();
            for (TableColumn column : columns) {
                
                
                value.add(
                    (String) column.
                    getCellObservableValue(row).getValue());
                
            }
            values.add(value);
          } 
         
         for(int i=0; i<values.size(); i++){
             System.out.println(values.get(i));
                     
         }
        return values;
    }
    /*
    *Method invoked when a user clicks a row
    *Only used for testing purposes 
    */
    @FXML 
    public void handleSelected() throws Exception{
        
        TablePosition pos = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
        int index = pos.getRow();
        String selected = table.getItems().get(index).toString();
        //selected = selected.substring(1, selected.indexOf(",")); //only get username
        System.out.println(selected);   
        
    }
    /*
     * Method determines how the the table was accessed and by who
     * The corresponding actions are then taken to the dispaly only the correct information for that user
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        String user = CurrentUser.getUserName();
        String role = CurrentUser.getUserRole();
        
        System.out.println(role);
         // Only show back button if activated via viewMarksT
        if(!StudentMarkClick.isClicked()){
            back.setVisible(false);
        }
 
        //only show marks for particular sudent
        if(role.equals("S") &&StudentMarkClick.isClicked()){
          
            SN.setText(StudentMarkClick.getStunum());
            CC.setText(StudentMarkClick.getCourse());
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);

            StudentMarkClick.setClicked(false);
            try{
                this.getDetails();
            }
            catch (IOException | SQLException e) {
                
            }

        }
        //Only show detail for particular student only coming from viewMarkT table
        else if(StudentMarkClick.isClicked()){
            
            SN.setText(StudentMarkClick.getStunum());
            CC.setText(StudentMarkClick.getCourse());
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
          
            //deactivate but store search to go back
            StudentMarkClick.setClicked(false);
            try{
                this.getDetails();
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
 
    }    
    
}