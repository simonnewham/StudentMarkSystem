/*
 * Class responsible for all actions taken by the admin
 */
package Users;

import UI.Admin_RoleController;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *CSC3003S CAPSTONE
 * @author NWHSIM001, GRNCAM007, WLLCOU004
 */
public class Admin extends User{
    
    public Admin(String u,String f, String l,  String e, String p, String r) {
        super(u, f, l, e, p, r);
    }
    
    /*Adds a new User to the database with the given details of the user.
    Gives an error message if the user already exists in the database*/
    public static void handleAddUser(String firstname, String surname, String username, String password, String role) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        try{
            String addUser = "INSERT INTO `users`.`users_login` (`user_id`, `first_name`, `surname`, `password`, `role`) VALUES ('" + username + "', '" + firstname +"', '" + surname + "', '" + password + "', '" + role + "')";
             myStatement.executeUpdate(addUser);
        }
        catch(SQLException e){
            Admin_RoleController.Error();
            System.out.println("Error! "+username+" may already be in database");
        }
        myConn.close();
    }
    
    /*Removes a user that exists in the databse.
    Gives an error if the user is not found and therefore cannot be removed*/
    public static void handleRemoveUser(String username) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        try{
            String addUser = "DELETE FROM `users`.`users_login` WHERE `user_id`='" + username + "'";
            myStatement.executeUpdate(addUser);
        }
        catch(SQLException e){
           
            System.out.println("Invalid Username");
        }
        
        myConn.close();
    }
    
    /*Reads through a .csv file and adds all the users in the file with their details into the databse*/
    public static void handleImportUser(String fileName) throws SQLException, FileNotFoundException{
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()){
            String tempString = s.next();
            String[] stringList = tempString.split(";");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            try{
                
            String addUser = "INSERT INTO `users`.`users_login` (`user_id`, `first_name`, `surname`, `password`, `role`) VALUES ('" + stringList[0] + "', '" + stringList[1] +"', '" + stringList[2] + "', '" + stringList[3] + "', '" + stringList[4] + "')";
            myStatement.executeUpdate(addUser);
            }
            
            catch(SQLException e){
                Admin_RoleController.Error();
                System.out.println("Error "+stringList[0]+" may already be in database");
        }
            
            myConn.close();
        }
    }
    
}
