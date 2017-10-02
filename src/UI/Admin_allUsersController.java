/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Users.User;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author simonnewham
 */
public class Admin_allUsersController implements Initializable {
    
    @FXML private TableView<User> table;
    @FXML public TableColumn<User, String> user_id;
    @FXML TableColumn<User, String> first_name;
    @FXML TableColumn<User, String> surname;
    @FXML TableColumn<User, String> email;
    @FXML TableColumn<User, String> password;
    @FXML TableColumn<User, String> role;
    
    public ObservableList<User> data = FXCollections.observableArrayList(
            
        );
    
    private void getData() throws FileNotFoundException, IOException, SQLException{
        
        
        //data = FXCollections.observableArrayList();
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("select * from users_login");
        
        /*user_id.setCellValueFactory(new PropertyValueFactory("username"));
        first_name.setCellValueFactory(new PropertyValueFactory("first"));
        surname.setCellValueFactory(new PropertyValueFactory("last"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        password.setCellValueFactory(new PropertyValueFactory("password"));
        role.setCellValueFactory(new PropertyValueFactory("role"));

       /* while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }*/
        
        while (rs.next()){
            data.add(new User(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")

            ));
            //System.out.println(data.get(0).getUser());
            //table.setItems(data);
        
            
        }
        
        
        myConn.close();
    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load logo
        user_id.setCellValueFactory(new PropertyValueFactory<User, String> ("username"));
        first_name.setCellValueFactory(new PropertyValueFactory<User, String> ("first"));
        surname.setCellValueFactory(new PropertyValueFactory <User, String>("last"));
        email.setCellValueFactory(new PropertyValueFactory <User, String>("email"));
        password.setCellValueFactory(new PropertyValueFactory <User, String>("password"));
        role.setCellValueFactory(new PropertyValueFactory <User, String>("role"));
        
        try{
            this.getData();
        }
        catch (IOException | SQLException e) {
  
        }
        table.setItems(data);
   
    }  
}
