/*
 * Controller responsible for displaying all the users in a table for the Admin role
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
 * CSC3003S CAPESTONE
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class Admin_allUsersController implements Initializable {
    
    @FXML private TableView<User> table;
    @FXML public TableColumn<User, String> user_id;
    @FXML TableColumn<User, String> first_name;
    @FXML TableColumn<User, String> surname;
    @FXML TableColumn<User, String> email;
    @FXML TableColumn<User, String> password;
    @FXML TableColumn<User, String> role;
    
    /*
     Observable list is loaded with the rows whoch are to be displayed in the table
    */
    public ObservableList<User> data = FXCollections.observableArrayList(
            
        );
    /*
     * Method is responsible for reading all the data from the users_login table and loading it into data
    */
    private void getData() throws FileNotFoundException, IOException, SQLException{
  
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        ResultSet rs = myStatement.executeQuery("select * from users_login");
  
        while (rs.next()){
            data.add(new User(
                    rs.getString("user_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")

            ));
            //TRACING
            //System.out.println(data.get(0).getUser());
            //table.setItems(data);
       
        }
        
        myConn.close();
    } 
    /*
     * Method responsible for setting up the fixed colums in the table as well as intiating the getData() method
     * Loads the data into the table
    */
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
