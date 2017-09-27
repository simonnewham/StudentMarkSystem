/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import DB.DataBase;
import Users.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author simonnewham
 */
public class Convenor extends User{
    
    
    public Convenor(String u,String p, String r){
        
        super(u,p,r);
       
    }
    
    public static void importMarks(String fileName) throws FileNotFoundException, IOException, SQLException{
        
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String currentLine;
        
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "capstone"); 
        Statement myStatement = myConn.createStatement();
        String testName = br.readLine();
        String insertColumn =   "ALTER TABLE users.marks ADD "+ testName +" INT";
                    
        myStatement.executeUpdate(insertColumn);        
        while ((currentLine = br.readLine()) != null){
            String[] lineParts = currentLine.split(" ");
            String studentName = lineParts[0];
            String testMarkString = lineParts[1];
            int testMark = Integer.parseInt(testMarkString);
            
            String insertMark = "UPDATE users.marks SET " + testName + "='" + testMarkString + "' WHERE studentname='" + studentName + "' and coursename ='CSC3003S'";
            myStatement.executeUpdate(insertMark);    
        }
        
    }
    
    public static void editMarks(String studentName, String markInfo, String newMark) throws SQLException{
        Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "capstone"); 
        Statement myStatement = myConn.createStatement();
        
        String editMark = "UPDATE users.marks SET " + markInfo + "='" + newMark + "' WHERE studentname='" + studentName + "' and coursename ='CSC3003S'";
        myStatement.executeUpdate(editMark);
    }
    
}
