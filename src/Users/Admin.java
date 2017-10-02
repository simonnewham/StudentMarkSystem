/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Campbell
 */
public class Admin extends User{
    
    public Admin(String u,String f, String l,  String e, String p, String r) {
        super(u, f, l, e, p, r);
    }
    public static void handleAddUser(String firstname, String surname, String username, String password, String role) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        String addUser = "INSERT INTO `users`.`users_login` (`user_id`, `first_name`, `surname`, `password`, `role`) VALUES ('" + username + "', '" + firstname +"', '" + surname + "', '" + password + "', '" + role + "')";
        myStatement.executeUpdate(addUser);
        myConn.close();
    }
    
    public static void handleRemoveUser(String username) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
        Statement myStatement = myConn.createStatement();
        String addUser = "DELETE FROM `users`.`users_login` WHERE `user_id`='" + username + "'";
        myStatement.executeUpdate(addUser);
        myConn.close();
    }
    
    public static void handleImportUser(String fileName) throws SQLException, FileNotFoundException{
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext()){
            String tempString = s.next();
            String[] stringList = tempString.split(";");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "simnew96"); 
            Statement myStatement = myConn.createStatement();
            String addUser = "INSERT INTO `users`.`users_login` (`user_id`, `first_name`, `surname`, `password`, `role`) VALUES ('" + stringList[0] + "', '" + stringList[1] +"', '" + stringList[2] + "', '" + stringList[3] + "', '" + stringList[4] + "')";
            myStatement.executeUpdate(addUser);
            myConn.close();
        }
    }
    
}
