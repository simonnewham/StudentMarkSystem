/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author simonnewham
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
    
    @FXML public void goBack() throws FileNotFoundException, IOException, SQLException{
        
        StudentMarkClick.setBack(true);
        content.getChildren().clear();
        content.getChildren().add(FXMLLoader.load(getClass().getResource("viewMarksT.fxml")));   
    }
    
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
            for(int i=0;i<courses.size(); i++){
                 String table1 = courses.get(i).toLowerCase()+"_marks";
                 ResultSet rs1 = myStatement.executeQuery("SELECT * FROM users."+table1+" WHERE studentname='"+search.toUpperCase()+"'");
            
                    this.getData_and_Set(rs1);
            }
             myConn.close();
            }
        else{
            //this.getMarks();
        }
        SN.clear();
     }
    
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
    
    @FXML 
    public void getData_and_Set(ResultSet RS) throws FileNotFoundException, IOException, SQLException{
        
        //data.clear();
        ResultSet rs = RS;
        //table.getColumns().clear();
        
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
    public void getDetails() throws FileNotFoundException, IOException, SQLException{
        
        String sn = SN.getText();
        String cc = CC.getText().toLowerCase()+"_marks";
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("SELECT * FROM users."+cc+" WHERE "+cc+".studentname ='"+sn+"'");
        
        this.getData_and_Set(rs);
        myConn.close();
    }
    
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
        // Only show back button if activated via viewMarksT
        
         String user = CurrentUser.getUserName();
        String role = CurrentUser.getUserRole();
        
        System.out.println(role);
        
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
            
            //StudentMarkClick.clearClicked();
            //deactivate but store search to go back
            StudentMarkClick.setClicked(false);
            try{
                this.getDetails();
            }
            catch (IOException | SQLException e) {
                
            }
//            SN.setText(user);
//            SN.setVisible(false);
//            CC.setVisible(false);
//            CC.setVisible(false);
//            searchSN.setVisible(false);
//            searchCC.setVisible(false);
//
//            try{
//                this.getStudent();
//            }
//            catch (IOException | SQLException e) {
//                
//            }

        }
        //Only show detail for particular student only coming from viewMarkT table
        else if(StudentMarkClick.isClicked()){
            
            SN.setText(StudentMarkClick.getStunum());
            CC.setText(StudentMarkClick.getCourse());
            SN.setVisible(false);
            CC.setVisible(false);
            searchSN.setVisible(false);
            searchCC.setVisible(false);
            
            //StudentMarkClick.clearClicked();
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